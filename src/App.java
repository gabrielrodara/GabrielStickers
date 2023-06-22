import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_b01v2c0s";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDb();

        // String url = "https://api.nasa.gov/planetary/apod?api_key=hWxmNXqeUo0CcidM6eyb36aoesdBpk1SpHuJj5Va&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);
        
        // exibir e manipular os dados
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, "saida/" + conteudo.getTitulo() + ".png");

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
