package testeadapt3.cursoandroid2.com.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.editTextEditar)
    EditText editTextEditar;

    @BindView(R.id.textViewMostrar)
    TextView textViewMostrar;

    @BindView(R.id.botao)
    Button botao;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.anotacoes)
    EditText anotacooes;

    RadioButton radioButtonSelecionado;

    public static final String ARQUIVO_PREFERENCIA = "arquivo_preferencia";
    public static final String ARQUIVO_ANOTACAO = "arquivo_de_anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );
        botao.setOnClickListener( this );

        //recuperar dados salvos na preferencia: para que ao iniciar possamos ter acesso a ultimo dados salvo
        SharedPreferences sharedPreferences = getSharedPreferences( ARQUIVO_PREFERENCIA, MODE_PRIVATE );

        if (sharedPreferences.contains( "nome" ) && sharedPreferences.contains( "corEscolhida" ) && lerArquivo() != null) {
            String nomeUsuario = sharedPreferences.getString( "nome", "Usuario ñ definido" ); // vamos preparar string para receber dados salvos anterioremente para depois utilizar
            String corRecuperada = sharedPreferences.getString( "corEscolhida", "Laranja" );
            anotacooes.setText( lerArquivo() );
            textViewMostrar.setText( "Olá, " + nomeUsuario + " tcor: " + corRecuperada + " Texto: " + anotacooes.getText().toString());
            setBackgroud( corRecuperada );

        } else {
            textViewMostrar.setText( "Olá, Usuario n definido" );
        }
//        if (lerArquivo() != null) {
//            anotacooes.setText( lerArquivo() );
//
//        } else {
//            Toast.makeText( getApplicationContext(), "sem anotacoes salvas", Toast.LENGTH_LONG ).show();
//        }

    }



    @Override
    public void onClick(View v) {

        int view = v.getId();
        int idRadioButtonEscolhido = radioGroup.getCheckedRadioButtonId();

        if (view == R.id.botao) {
            SharedPreferences sharedPreferences = getSharedPreferences( ARQUIVO_PREFERENCIA, MODE_PRIVATE );
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (editTextEditar.getText().toString().isEmpty() || idRadioButtonEscolhido <= 0 || anotacooes.getText().toString().isEmpty()) {
                editTextEditar.setError( "Requer digitar seu nome" );
                anotacooes.setError( "Anotacao vazia...precisa digitar algo." );
                Toast.makeText( getApplicationContext(), "escolha um cor", Toast.LENGTH_LONG ).show();

            } else if (idRadioButtonEscolhido > 0 && !editTextEditar.getText().toString().isEmpty() && !anotacooes.getText().toString().isEmpty()) {
                String textoDigitado = anotacooes.getText().toString();
                radioButtonSelecionado = findViewById( idRadioButtonEscolhido );
                String corEscolhida = radioButtonSelecionado.getText().toString();
                editor.putString( "corEscolhida", corEscolhida );
                editor.putString( "nome", editTextEditar.getText().toString() );
                editor.commit();
                setBackgroud( corEscolhida );
                textViewMostrar.setText( "Olá, " + editTextEditar.getText().toString() + " Cor- " + corEscolhida + " texto: " + textoDigitado );
                gravarArquivo( textoDigitado );
            }
//            } else if (!anotacooes.getText().toString().isEmpty()) {
//                String textoDigitado = anotacooes.getText().toString();
//                gravarArquivo( textoDigitado );
//
//            } else {
//                anotacooes.setError( "Anotacao vazia precisa digitar algo." );
//            }
        }
    }

    private void gravarArquivo(String texto) {

        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter( openFileOutput( ARQUIVO_ANOTACAO, Context.MODE_PRIVATE ) );
            outputStreamWriter.write( texto );
            outputStreamWriter.close();
            Toast.makeText( getApplicationContext(), "anotações foram salvas!", Toast.LENGTH_LONG ).show();

        } catch (IOException e) {
            Log.v( "stream", e.toString() );
        }
    }

    private String lerArquivo() {
        String resultado = "";

        try {
            //abrir arquivo
            InputStream arquivo = openFileInput( ARQUIVO_ANOTACAO );

            if (arquivo != null) {
                //ler arquivo
                InputStreamReader inputStreamReader = new InputStreamReader( arquivo );

                //gerar Buffer do arquivo que foi lido , para recuperar as informacoes que estao dentro do arquivo
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );

                //recuperar textos do arquivo
                //bufferedReader.readLine(); //redLine ler cada linha
                String linhaArquivo = "";
                while ((linhaArquivo = bufferedReader.readLine()) != null) {
                    resultado += linhaArquivo;

                }
                //fechamos o arquivo
                arquivo.close();
            }

        } catch (IOException e) {
            Log.v( "stream", e.toString() );

        }
        return resultado;
    }

    private void setBackgroud(String cor) {
        switch (cor) {
            case "Azul":
                layout.setBackgroundColor( getResources().getColor( R.color.corEscolhidaAzul ) );
                break;
            case "Laranja":
                layout.setBackgroundColor( getResources().getColor( R.color.corEscolhidaLaranja ) );
                break;
            case "Verde":
                layout.setBackgroundColor( getResources().getColor( R.color.corEscolhidaVerde ) );
                break;
        }
    }
}
