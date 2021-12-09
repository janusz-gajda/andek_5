package eu.unuel.lab5;
import android.content.Context;
import android.opengl.GLSurfaceView;


public class MyGLSurfaceView extends GLSurfaceView
{
    private MyRenderer renderer;
    public MyGLSurfaceView(Context context)
    {
        super(context);
        renderer = new MyRenderer();
        setRenderer(renderer);
    }
    public void updateXOffset(float newVal){
        renderer.updateXOffset(newVal);
    }