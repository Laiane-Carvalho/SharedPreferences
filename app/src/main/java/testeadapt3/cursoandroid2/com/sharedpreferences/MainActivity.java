package testeadapt3.cursoandroid2.com.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editTextEditar)
    EditText editTextEditar;

    @BindView(R.id.textViewMostrar)
    TextView textViewMostrar;

    @BindView(R.id.botao)
    Button botao;

    public static final String ARQUIVO_PREFERENCIA = "arquivo_preferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );

        botao.setOnClickListener( this );
        //recuperar dados salvos na preferencia: para que ao iniciar possamos ter acesso a ultimo dados salvo
        SharedPreferences sharedPreferences = getSharedPreferences( ARQUIVO_PREFERENCIA, MODE_PRIVATE );
        if (sharedPreferences.contains( "nome" )) {
            String nomeUsuario = sharedPreferences.getString( "nome", "Usuario ñ definido" ); // vamos preparar string para receber dados salvos anterioremente para depois utilizar
            textViewMostrar.setText( "Olá, " + nomeUsuario );
        } else {
            textViewMostrar.setText( "Olá, Usuario n definido" );
        }


    }

    @Override
    public void onClick(View v) {
        int view = v.getId();
        if (view == R.id.botao) {
            SharedPreferences sharedPreferences = getSharedPreferences( ARQUIVO_PREFERENCIA, MODE_PRIVATE );
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (editTextEditar.getText().toString().isEmpty()) {
                editTextEditar.setError( "Requer digitar algo" );
            } else {
                editor.putString( "nome", editTextEditar.getText().toString() );
                editor.commit();
                textViewMostrar.setText( "Olá, " + editTextEditar.getText().toString() );
            }
        }
    }
}
