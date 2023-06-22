import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json){
        // extrair só os dados que interessam (título e imagem)
        var parser = new JasonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            var conteudo = new Conteudo(atributos.get("title"), atributos.get("url"));
            conteudos.add(conteudo);
        }
        return conteudos;
    }
}
