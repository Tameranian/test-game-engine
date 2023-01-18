package com.base.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.windows.WindowProc;

import static org.lwjgl.glfw.GLFW.glfwSwapInterval;

public class Window
{

    private int width, height;
    private String title;
    public static long window;
    boolean vsync;
    public Input input;

    public Window(int width, int height, String title,  boolean vsync)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.vsync = vsync;

        if (vsync)
        {
            glfwSwapInterval(1);
        }
    }


    public void create()
    {
        input = new Input();
         if (!GLFW.glfwInit())
         {
             System.err.println("ERROR: Couldn't initialize GLFW");
             return;

         }
            window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

         if (window == 0 )
         {
             System.err.println("ERROR: Window Wasn't created");
             return;
         }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window,(videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());


        GLFW.glfwShowWindow(window);

    }


    public void update()
    {
        GLFW.glfwPollEvents();
    }

    public void destroy()
    {
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
        input.destroy();
    }


    public void swapBuffers()
    {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose()
    {
        return GLFW.glfwWindowShouldClose(window);
    }
}
