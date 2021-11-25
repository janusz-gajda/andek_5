public class MyRenderer implements GLSurfaceView.Renderer
{
    private float x = 0.0f;
    private float xOffset = 0.0f;
    private int points=360;
    private float vertices[]={0.0f,0.0f,0.0f};
    private FloatBuffer vertBuff;

    boolean alreadyPlayedPilka = false;
    public MyRenderer(){
        vertices=new float[(points+1)*3];
        for(int i=3;i<(points+1)*3;i+=3){
            double rad=(i*360/points*3)*(3.14/180);
            vertices[i]=(float)Math.sin(rad);
            vertices[i+1]=(float)Math.cos(rad);
            vertices[i+2]=-1.0f;
            //System.out.println(rad + " " + vertices[i] + " " + vertices[i+1]);
        }
        ByteBuffer bBuff=ByteBuffer.allocateDirect(vertices.length*4);
        bBuff.order(ByteOrder.nativeOrder());
        vertBuff=bBuff.asFloatBuffer();
        vertBuff.put(vertices);
        vertBuff.position(0);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl,45.0f,(float)width/(float)height,-1.0f, -10.0f);
        gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glPushMatrix();
        gl.glTranslatef(0, 0, 0);
        gl.glScalef(0.2f, 0.5f, 1.0f);
        gl.glColor4f(0.0f,0.0f,1.0f, 1.0f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);

        f(!(x + xOffset > 4.2f || x+xOffset < -4.2f)){
            gl.glTranslatef(x += xOffset,0.0f,0.0f);
            if(x > -4.19f && x < 4.19f){
                if(alreadyPlayedPilka)
                    alreadyPlayedPilka = false;
            }

        } else{
            if(!alreadyPlayedPilka){
                Globals.playPilka = true;
                alreadyPlayedPilka = true;
                Globals.counter++;
            }
            gl.glTranslatef(x,0.0f,0.0f);
        }
        
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, points/2);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glPopMatrix();
    }
    public void updateXOffset(float newVal){
            xOffset = newVal;
        }
}