#SharedPreferences ou Preferencias de Usuário=

 É uma forma de armazenamento de dados.
 As vezes precisamos persistir dados durante o uso da aplicação, para que possam ser utilizados posteriormente, como preferências de usuário, indicação de primeira vez do uso do aplicativo, e justamente quando estes dados são consideravelmente pequenos não vale a pena criar um banco de dados. 
Com ele, é possiível salvar informações e deixar o app mais dinâmico..
podemos utilizar para salvar pontuaçoões de jogos, dados do usuário ...preferências do usuário, configuracoes do app, como papel de parede e etc...


#####Neste simples exemplo de App, estou demonstando como Gravar, ler e recuperar:
 *****arquivos de preferencias...***
 
 ****configuracoes como cor painel...***
 
 ****arquivo de texto***
 
 
  ##Para maiores conhecimentos
  `public InputStreamReader (InputStream in)  `      
 Cria um InputStreamReader para ler o InputStream fornecido usando a codificação do conjunto de caracteres padrão.
  
  `public InputStreamReader (InputStream in, Charset c)`
   Cria um InputStreamReader para ler o InputStream fornecido usando a codificação do conjunto de caracteres.
   
`public InputStreamReader (InputStream i, CharsetDecoder c)`
  Cria um InputStreamReader para ler a partir do InputStream fornecido usando o decodificador de conjunto de caracteres fornecido.
  
`  public InputStreamReader (InputStream in, String enc)` 
  lança UnsupportedEncodingException 
  Cria um InputStreamReader para ler o InputStream fornecido usando a codificação do conjunto de caracteres nomeados. Se a codificação nomeada não for suportada, uma UnsupportedEncodingException será lançada.
  
  `public OutputStreamWriter (OutputStream out) `                                                                                                                                                                                       
  Cria um OutputStreamWriter para gravar no OutputStream fornecido usando a codificação do conjunto de caracteres padrão.
  
  `public OutputStreamWriter (OutputStream out, Charset c)` 
  Cria um OutputStreamWriter para gravar no OutputStream fornecido usando a codificação do conjunto de caracteres.
  
  `public OutputStreamWriter(OutputStream o, CharsetEncode c)`                                                                                                  
  Cria um OutputStreamWriter para gravar no OutputStream fornecido usando o codificador do conjunto de caracteres fornecido.
  
  `public OutputStreamWriter (OutputStream out, String enc)` 
  lança UnsupportedEncodingException 
  Cria um OutputStreamWriter para gravar no OutputStream fornecido usando a codificação do conjunto de caracteres nomeados. Se a codificação nomeada não for suportada, uma UnsupportedEncodingException será lançada.
  
  ####Resumindo, são classe que lhe ajudam a ler e gravar arquivos locais corretamente de maneira consistente, com base em Unicode, usando a codificação local. 