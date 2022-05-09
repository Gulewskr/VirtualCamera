import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.*;
import org.ejml.simple.SimpleMatrix;

public class Scene extends JFrame {

    private java.util.List<DrawObject> objectList = new LinkedList<DrawObject>();
    private int screenWidth;
    private int screenHeight;
    private double a;
    private double focal = -10;

    private double TranslationStep = 0.1;
    private double RotationStep = Math.toRadians(0.01);
    private double ZoomStep = 0.25;

    private int fps = 2;

    private double[][] TranslationMatrix = { { 1, 0, 0, 0} , {0, 1, 0, 0} , {0, 0, 1, 0} , {0, 0, 0, 1} };
    private double[][] RotationMatrixX = { { 1, 0, 0, 0} , {0, 1, 0, 0} , {0, 0, 1, 0} , {0, 0, 0, 1} };
    private double[][] RotationMatrixY = { { 1, 0, 0, 0} , {0, 1, 0, 0} , {0, 0, 1, 0} , {0, 0, 0, 1} };
    private double[][] RotationMatrixZ = { { 1, 0, 0, 0} , {0, 1, 0, 0} , {0, 0, 1, 0} , {0, 0, 0, 1} };

    private void resetMatrixes() {
        TranslationMatrix = new double[][] { { 1, 0, 0, 0} , {0, 1, 0, 0} , {0, 0, 1, 0} , {0, 0, 0, 1} };
        RotationMatrixX = new double[][] { { 1, 0, 0, 0} , {0, 1, 0, 0} , {0, 0, 1, 0} , {0, 0, 0, 1} };
        RotationMatrixY = new double[][] { { 1, 0, 0, 0} , {0, 1, 0, 0} , {0, 0, 1, 0} , {0, 0, 0, 1} };
        RotationMatrixZ = new double[][] { { 1, 0, 0, 0} , {0, 1, 0, 0} , {0, 0, 1, 0} , {0, 0, 0, 1} };
    }

    private Movement control;

    public Scene(int width, int height)
    {
        super();
        this.setSize(width, height);
        screenWidth = width;
        screenHeight = height;
        a = (float)screenWidth/screenHeight;
        initScene();
    }

    public void initScene()
    {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/SceneObjects"));
            String line = reader.readLine();
            objectList.add(new DrawObject());
            int index = 0;
            while (line != null) {
                System.out.println(line);
                String[] cords = line.split(",");
                if(cords.length == 6)
                {
                    objectList.get(index).addEdge(
                            Float.parseFloat(cords[0]), Float.parseFloat(cords[1]), Float.parseFloat(cords[2]),
                            Float.parseFloat(cords[3]), Float.parseFloat(cords[4]), Float.parseFloat(cords[5]));
                } else {
                    index++;
                    objectList.add(new DrawObject());
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        control = new Movement();
        this.addKeyListener( control );
    }

    public double normalizeX(double x)
    {
        return a * (1/Math.tan(1)) * x;
    }

    public double normalizeY(double y)
    {
        return (1/Math.tan(1)) * y;
    }

    public double normalizeZ(double x)
    {
        //double zFar
        return a * (1/Math.tan(1)) * x;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0x000000));
        g2.fill(new Rectangle(screenWidth, screenHeight));
        g2.setColor(new Color(0xFFFFFF));
        for(DrawObject d : objectList)
        {
            for(Line2D l : d.getEdges((float)focal, screenWidth, screenHeight))
            {
                g2.draw(l);
            }
        }
    }

    public void logic_step()
    {
        resetMatrixes();
        if (control.MoveForward) {
            TranslationMatrix[2][3] -= TranslationStep;
        }
        if (control.MoveBackward) {
            TranslationMatrix[2][3] += TranslationStep;
        }
        if (control.MoveLEFT) {
            TranslationMatrix[0][3] -= TranslationStep;
        }
        if (control.MoveRIGHT) {
            TranslationMatrix[0][3] += TranslationStep;
        }
        if (control.MoveUP) {
            TranslationMatrix[1][3] += TranslationStep;
        }
        if (control.MoveDOWN) {
            TranslationMatrix[1][3] -= TranslationStep;
        }

        if (control.RotateZR) {
            RotationMatrixZ[0][0] = Math.cos(RotationStep);
            RotationMatrixZ[0][1] = -1 * Math.sin(RotationStep);
            RotationMatrixZ[1][0] = Math.sin(RotationStep);
            RotationMatrixZ[1][1] = Math.cos(RotationStep);
        }
        if (control.RotateZL) {
            RotationMatrixZ[0][0] = Math.cos(-RotationStep);
            RotationMatrixZ[0][1] = -1 * Math.sin(-RotationStep);
            RotationMatrixZ[1][0] = Math.sin(-RotationStep);
            RotationMatrixZ[1][1] = Math.cos(-RotationStep);
        }
        if (control.RotateYR) {
            RotationMatrixY[0][0] = Math.cos(RotationStep);
            RotationMatrixY[0][2] = Math.sin(RotationStep);
            RotationMatrixY[2][0] = -1 * Math.sin(RotationStep);
            RotationMatrixY[2][2] = Math.cos(RotationStep);
        }
        if (control.RotateYL) {
            RotationMatrixY[0][0] = Math.cos(-RotationStep);
            RotationMatrixY[0][2] = Math.sin(-RotationStep);
            RotationMatrixY[2][0] = -1 * Math.sin(-RotationStep);
            RotationMatrixY[2][2] = Math.cos(-RotationStep);
        }
        if (control.RotateXR) {
            RotationMatrixY[1][1] = Math.cos(RotationStep);
            RotationMatrixY[1][2] = -1 * Math.sin(RotationStep);
            RotationMatrixY[2][1] = Math.sin(RotationStep);
            RotationMatrixY[2][2] = Math.cos(RotationStep);
        }
        if (control.RotateXL) {
            RotationMatrixY[1][1] = Math.cos(-RotationStep);
            RotationMatrixY[1][2] = -1 * Math.sin(-RotationStep);
            RotationMatrixY[2][1] = Math.sin(-RotationStep);
            RotationMatrixY[2][2] = Math.cos(-RotationStep);
        }
        if (control.ZoomOUT)  {
            if(focal + ZoomStep < 0)
                focal += ZoomStep;
        }
        if (control.ZoomIN) {
            focal -= ZoomStep;
        }

        for(DrawObject d : objectList)
        {
            d.transform(TranslationMatrix);
            d.transform(RotationMatrixX);
            d.transform(RotationMatrixY);
            d.transform(RotationMatrixZ);
        }
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        double nsConvert = 1000000000.0 / fps;
        double deltaT = 0;

        while (true)
        {
            long now = System.nanoTime();
            deltaT += (now - lastTime) / nsConvert;
            lastTime = now;

            while(deltaT >= 1) {
                logic_step();
                render();
            }
        }
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        paint(g);
        g.dispose();
        bs.show();
    }
}
