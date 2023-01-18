package com.base.engine;


public class MainComponent implements Runnable
{
    public static Window window;
    public static final int WIDTH = 1280, HEIGHT = 720  ;
    public static final double FRAME_CAP = 5000;

    private boolean isRunning;
    private Game game;


    public MainComponent()
    {
        RenderUtil.initGraphics();
        isRunning = false;
        game = new Game();

    }
    public void start()
    {
        if(isRunning)
            return;

        run();
    }

    public void stop()
    {
        if(!isRunning)
            return;

        isRunning = false;
    }
    public void run()
    {
        isRunning = true;

        int frames = 0;
        long frameCounter = 0;

        final double frameTime = 1.0 / FRAME_CAP;

        long lastTime =  Time.getTime();
        double unprocessedTime = 0;

        while (isRunning)
        {
            boolean render = false;

            long startTime =  Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double)Time.SECOND;
            frameCounter += passedTime;

            while (unprocessedTime > frameTime)
            {
                render = true;

                unprocessedTime -= frameTime;


                if(window.shouldClose())
                    stop();

                Time.setDelta(frameTime);

                game.input();
                game.update();

                if(frameCounter >= Time.SECOND)
                {
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }

            }
            if(render)
            {

                render();
                frames++;
                update();

            }
            else
            {
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        }
        cleanup();
    }
    public void update()
    {
        window.update();
    }

    private void render()
    {
        RenderUtil.clearScreen();
        game.render();
        window.swapBuffers();
    }
    private void cleanup()
    {
        window.destroy();
    }
    public static void main(String[] args)
    {
        window = new Window(WIDTH, HEIGHT, "Tam's Game", false);
        window.create();

        MainComponent game = new MainComponent();
        game.start();
    }
}