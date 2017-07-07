package mx.unam.primera.com.appmoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class NoConnectionActivity extends AppCompatActivity
{
    ProgressBar pbProgress;
    Thread tr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);

        pbProgress = (ProgressBar)findViewById(R.id.pbProgress);
        pbProgress.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed()
    {
        pbProgress.setVisibility(View.VISIBLE);
        try
        {
            tr = setThread();
            tr.start();
        }
        catch (Exception ex)
        {
            if(tr.isAlive())
            {
                try
                {
                    tr.wait();
                } catch (InterruptedException e)
                {
                    //e.printStackTrace();
                }
            }
        }
    }

    private Thread setThread()
    {
        Thread tr = new Thread()
        {
            @Override
            public void run()
            {
                final boolean boolError;
                boolean e = false;
                try
                {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } catch(Exception ex)
                {
                    Log.d("Error", "Error al intentar navegar");
                    Log.e("onBackPressed", ex.getMessage());

                    e = true;
                    //Toast.makeText(getApplicationContext(),
                    //       "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                }
                finally
                {
                    boolError = e;
                }

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        pbProgress.setVisibility(View.GONE);
                        if(boolError == true)
                        {
                            Toast.makeText(getApplicationContext(),
                                   "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        return tr;
    }
}
