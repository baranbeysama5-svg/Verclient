package com.verclient.ui.clickgui;

import com.verclient.module.Module;
import com.verclient.module.setting.BooleanSetting;
import com.verclient.module.setting.NumberSetting;
import com.verclient.module.setting.Setting;
import com.verclient.ui.component.VerclientTheme;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;
import java.util.List;

public class ModuleButton extends Component {
    private final Module module;
    private final Frame parent;
    private int x, y, width, height;
    private boolean open = false;
    private final List<Component> settings = new ArrayList<>();

    public ModuleButton(Module module, Frame parent) {
        this.module = module;
        this.parent = parent;
        this.width = parent.width;
        this.height = 16;
        
        for (Setting s : module.settings) {
            if (s instanceof BooleanSetting) {
                settings.add(new CheckBox((BooleanSetting) s, parent));
            } else if (s instanceof NumberSetting) {
                settings.add(new Slider((NumberSetting) s, parent));
            }
        }
    }

    @Override
    public void setOffset(int x, int y) {
        this.x = x;
        this.y = y;
        int yOffset = this.height;
        if (open) {
            for (Component comp : settings) {
                comp.setOffset(x, y + yOffset);
                yOffset += comp.getHeight();
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.fill(x, y, x + width, y + height, module.isEnabled() ? VerclientTheme.BUTTON_HOVER : VerclientTheme.BUTTON_NORMAL);
        guiGraphics.drawString(net.minecraft.client.Minecraft.getInstance().font, module.getName(), x + 5, y + 4, module.isEnabled() ? VerclientTheme.TEXT_TITLE : VerclientTheme.TEXT_BUTTON);

        if (open) {
            for (Component comp : settings) {
                comp.render(guiGraphics, mouseX, mouseY, partialTick);
            }
        }
    }
    
    @Override
    public int getHeight() {
        int h = height;
        if (open) {
            for (Component comp : settings) {
                h += comp.getHeight();
            }
        }
        return h;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                module.toggle();
                return true;
            } else if (button == 1) {
                open = !open;
                return true;
            }
        }
        if (open) {
            for (Component comp : settings) {
                if (comp.mouseClicked(mouseX, mouseY, button)) return true;
            }
        }
        return false;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (open) {
            for (Component comp : settings) {
                comp.mouseReleased(mouseX, mouseY, button);
            }
        }
    }
    
    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
