package xyz.cirno.wcgtest;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

public class GLActivity extends Activity {
    static final String TAG = "GLActivity";
    private GLSurfaceView glView;
    static final int EGL_GL_COLORSPACE_KHR = 0x309D;
    static final int EGL_GL_COLORSPACE_DISPLAY_P3_EXT = 0x3363;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.gl_test_title);
        final Context ctx = this;
        glView = new GLSurfaceView(this);
        glView.setEGLConfigChooser(8, 8, 8, 8, EGL10.EGL_DONT_CARE, EGL10.EGL_DONT_CARE);
        glView.setEGLWindowSurfaceFactory(new GLSurfaceView.EGLWindowSurfaceFactory() {
            @Override
            public EGLSurface createWindowSurface(EGL10 egl, EGLDisplay display, EGLConfig config, Object nativeWindow) {
                String extensionsStr = egl.eglQueryString(display, EGL10.EGL_EXTENSIONS);
                List<String> extensions = Arrays.asList(extensionsStr.split(" "));
                boolean hasColorspaceDisplayP3Ext = extensions.contains("EGL_EXT_gl_colorspace_display_p3");
                if(!hasColorspaceDisplayP3Ext) {
                    runOnUiThread(() -> {
                        Toast.makeText(ctx, R.string.no_egl_extension, Toast.LENGTH_LONG).show();
                        finish();
                    });
                }
                int[] attrs = new int[] {
                    EGL_GL_COLORSPACE_KHR, EGL_GL_COLORSPACE_DISPLAY_P3_EXT,
                    EGL10.EGL_NONE
                };
                return egl.eglCreateWindowSurface(display, config, nativeWindow, attrs);
            }

            @Override
            public void destroySurface(EGL10 egl, EGLDisplay display, EGLSurface surface) {
                egl.eglDestroySurface(display, surface);
            }
        });

        glView.setRenderer(new GLSurfaceView.Renderer() {
            int width;
            int height;
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {

            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                this.width = width;
                this.height = height;
            }

            @Override
            public void onDrawFrame(GL10 gl) {
                // sRGB red in linear Display P3 space (converted with gamma 2.2)
                GLES20.glClearColor(0.828f, 0.029f, 0.013f, 1f);
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

                GLES20.glEnable(GLES20.GL_SCISSOR_TEST);
                GLES20.glScissor(0, 0, width, height / 2);
                GLES20.glClearColor(1f, 0f, 0f, 1f);
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
                GLES20.glDisable(GLES20.GL_SCISSOR_TEST);
            }
        });
        glView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(glView);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}