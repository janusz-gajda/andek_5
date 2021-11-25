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
    //na 4
    public void updateXOffset(float newVal){
        renderer.updateXOffset(newVal);
    }
}
