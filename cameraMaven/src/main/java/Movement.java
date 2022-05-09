import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener
{
    // wsad
    public boolean MoveForward = false;
    public boolean MoveBackward = false;
    public boolean MoveLEFT = false;
    public boolean MoveRIGHT = false;
    // Q - up   E - down
    public boolean MoveUP = false;
    public boolean MoveDOWN = false;
    // 'Y' 'H'
    public boolean RotateXL = false;
    public boolean RotateXR = false;
    // 'G' 'J'
    public boolean RotateYL = false;
    public boolean RotateYR = false;
    // 'T' 'U'
    public boolean RotateZL = false;
    public boolean RotateZR = false;
    // 'I'  'O'
    public boolean ZoomIN = false;
    public boolean ZoomOUT = false;

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                MoveForward = true;
                break;
            case KeyEvent.VK_S:
                MoveBackward = true;
                break;
            case KeyEvent.VK_A:
                MoveLEFT = true;
                break;
            case KeyEvent.VK_D:
                MoveRIGHT = true;
                break;
            case KeyEvent.VK_Q:
                MoveUP = true;
                break;
            case KeyEvent.VK_E:
                MoveDOWN = true;
                break;
            case KeyEvent.VK_Y:
                RotateXL = true;
                break;
            case KeyEvent.VK_H:
                RotateXR = true;
                break;
            case KeyEvent.VK_G:
                RotateYL = true;
                break;
            case KeyEvent.VK_J:
                RotateYR = true;
                break;
            case KeyEvent.VK_T:
                RotateZL = true;
                break;
            case KeyEvent.VK_U:
                RotateZR = true;
                break;
            case KeyEvent.VK_O:
                ZoomOUT = true;
                break;
            case KeyEvent.VK_I:
                ZoomIN = true;
                break;

        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                MoveForward = false;
                break;
            case KeyEvent.VK_S:
                MoveBackward = false;
                break;
            case KeyEvent.VK_A:
                MoveLEFT = false;
                break;
            case KeyEvent.VK_D:
                MoveRIGHT = false;
                break;
            case KeyEvent.VK_Q:
                MoveUP = false;
                break;
            case KeyEvent.VK_E:
                MoveDOWN = false;
                break;
            case KeyEvent.VK_Y:
                RotateXL = false;
                break;
            case KeyEvent.VK_H:
                RotateXR = false;
                break;
            case KeyEvent.VK_G:
                RotateYL = false;
                break;
            case KeyEvent.VK_J:
                RotateYR = false;
                break;
            case KeyEvent.VK_T:
                RotateZL = false;
                break;
            case KeyEvent.VK_U:
                RotateZR = false;
                break;
            case KeyEvent.VK_O:
                ZoomOUT = false;
                break;
            case KeyEvent.VK_I:
                ZoomIN = false;
                break;

        }
    }
}