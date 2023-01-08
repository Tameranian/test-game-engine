package com.base.engine;

import org.lwjgl.glfw.GLFW;

import java.security.Key;

import static org.lwjgl.glfw.GLFW.*;

public class Game
{

    private boolean m_Vsync;


    public Game()
    {

    }
    public void input()
    {

        glfwSetKeyCallback(Window.window, (window, key, scancode, action, mods) -> {

            if (key == GLFW_KEY_ESCAPE)
            {
                switch (action)
                {
                    case GLFW_PRESS:
                        System.out.println("We've just pressed Escape!");
                        break;
                    case GLFW_REPEAT:
                        System.out.println("We've just held Escape!");
                        break;
                    case GLFW_RELEASE:
                        System.out.println("We've just released Escape!");
                        break;
                    default:
                        break;
                }
            }
/*            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                System.out.println("We've just pressed Escape!");*/
        });
        glfwSetMouseButtonCallback(Window.window, (window, button, action, mods) -> {

            if (button == GLFW_MOUSE_BUTTON_LEFT);
            {
                switch (action)
                {
                    case GLFW_PRESS:
                        System.out.println("We've just clicked at X" + Input.getMouseX() + ", Y:" + Input.getMouseY() + "!");
                        break;
                    case GLFW_REPEAT:
                        System.out.println("We've just held click!");
                        break;
                    case GLFW_RELEASE:
                        System.out.println("We've just released click at X" + Input.getMouseX() + ", Y:" + Input.getMouseY() + "!");
                        break;
                    default:
                        break;
                }
            }
/*            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                System.out.println("We've just pressed Escape!");*/
        });
    }
    public void update()
    {

    }
    public void render()
    {

    }
}
