import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEspecialistaExercicios {

    static class Regra {
        String objetivo;
        String nivel;
        boolean temRestricao;
        String recomendacao;
        String justificativa;

        public Regra(String objetivo, String nivel, boolean temRestricao, String recomendacao, String justificativa) {
            this.objetivo = objetivo;
            this.nivel = nivel;
            this.temRestricao = temRestricao;
            this.recomendacao = recomendacao;
            this.justificativa = justificativa;
        }

        public boolean avaliar(String objetivoFato, String nivelFato, boolean restricaoFato) {
            return this.objetivo.equalsIgnoreCase(objetivoFato) && this.nivel.equalsIgnoreCase(nivelFato) && this.temRestricao == restricaoFato;
        }
    }

    private List<Regra> baseDeConhecimento;

    public SistemaEspecialistaExercicios() {
        baseDeConhecimento = new ArrayList<>();
        inicializarBaseDeConhecimento();
    }

    private void inicializarBaseDeConhecimento() {

        // --- EMAGRECIMENTO ---

        baseDeConhecimento.add(new Regra("Emagrecimento", "Iniciante", true,
            "Caminhada na esteira (ritmo moderado), bicicleta ergométrica e hidroginástica.",
            "Como você é iniciante, possui restrição física e deseja emagrecer, são recomendados exercícios de baixíssimo impacto para evitar lesões, proteger suas articulações e permitir a adaptação gradual do seu organismo."));
        
        baseDeConhecimento.add(new Regra("Emagrecimento", "Iniciante", false,
            "Caminhada acelerada, circuitos funcionais leves e elíptico.",
            "Como você é iniciante, não possui restrições e quer emagrecer, focamos em atividades aeróbicas contínuas para iniciar a ativação metabólica e construir uma base de resistência cardiovascular com segurança."));
        
        baseDeConhecimento.add(new Regra("Emagrecimento", "Intermediário", true,
            "Treino no elíptico, natação e musculação em formato de circuito com cargas moderadas.",
            "Dado seu nível intermediário com restrição física, o foco em emagrecimento exige otimização do gasto calórico através de exercícios de resistência muscular localizada e aeróbicos de baixo impacto articular."));
        
        baseDeConhecimento.add(new Regra("Emagrecimento", "Intermediário", false,
            "Corrida leve, Treino HIIT (Alta Intensidade) moderado e musculação funcional.",
            "Por ser intermediário e livre de restrições, seu corpo responde bem a variações de intensidade. O HIIT acelerará seu metabolismo, promovendo alto gasto calórico mesmo após o término do treino (efeito EPOC)."));
        
        baseDeConhecimento.add(new Regra("Emagrecimento", "Avançado", true,
            "HIIT na bicicleta indoor, natação de alta intensidade e musculação em aparelhos guiados.",
            "Sendo avançado mas com restrição, aplicamos estímulos de alta intensidade metabólica utilizando modalidades que isolam e protegem a sua limitação mecânica/articular específica."));
        
        baseDeConhecimento.add(new Regra("Emagrecimento", "Avançado", false,
            "Corrida de alta intensidade, Treino HIIT severo e Cross Training.",
            "Como aluno avançado e sem restrições, o foco é a máxima eficiência na oxidação de gordura através de treinos altamente dinâmicos, que desafiam sua potência aeróbica e anaeróbica de forma integrada."));

        // --- GANHO DE MASSA MUSCULAR (HIPERTROFIA) ---

        baseDeConhecimento.add(new Regra("Ganho de Massa", "Iniciante", true,
            "Musculação focada em máquinas guiadas (Leg Press, Peck Deck), com cargas leves a moderadas.",
            "Para iniciantes com restrição focados em hipertrofia, as máquinas guiadas oferecem a estabilidade necessária para isolar a musculatura alvo, gerando estímulo de crescimento sem estressar a região limitada."));
        
        baseDeConhecimento.add(new Regra("Ganho de Massa", "Iniciante", false,
            "Musculação básica com foco em exercícios multiarticulares (Supino com barra, Agachamento Taça) e pesos livres adaptados.",
            "Sendo iniciante sem restrições, o objetivo é o aprendizado motor e a coordenação intra e intermuscular, assentando as bases de força para suportar futuras sobrecargas hipertróficas."));
        
        baseDeConhecimento.add(new Regra("Ganho de Massa", "Intermediário", true,
            "Musculação combinando polias e máquinas com controle estrito de cadência e amplitude adaptada.",
            "No nível intermediário com restrição, induzimos a hipertrofia através do aumento do 'tempo sob tensão' nas polias e cabos, o que gera grande estresse mecânico no músculo sem sobrecarregar as articulações fragilizadas."));
        
        baseDeConhecimento.add(new Regra("Ganho de Massa", "Intermediário", false,
            "Treino de força tradicional com pesos livres pesados (Divisão de treino ABC ou ABCD), com técnicas de sobrecarga progressiva.",
            "Sua condição intermediária livre de lesões permite a manipulação de cargas elevadas em pesos livres. Isso recruta mais unidades motoras, gerando o estresse tensional e microlesões necessárias para a hipertrofia."));
        
        baseDeConhecimento.add(new Regra("Ganho de Massa", "Avançado", true,
            "Musculação com técnicas avançadas de isolamento (Drop-sets, Rest-pause) aplicadas estritamente em aparelhos biomecanicamente seguros.",
            "Como atleta avançado com restrição, usamos estratégias metabólicas intensas (como exaustão localizada) que quebram o platô muscular, mantendo o vetor de força longe da sua limitação física."));
        
        baseDeConhecimento.add(new Regra("Ganho de Massa", "Avançado", false,
            "Treino de alta intensidade com pesos livres, técnicas de falha concêntrica (Bi-sets, Drop-sets) e foco em grandes grupamentos.",
            "Para o nível avançado sem restrições, aplicamos o princípio da exaustão total. O uso de pesos livres associado a métodos de falha concêntrica maximiza as respostas hormonais e a síntese proteica adaptativa."));

        // --- CONDICIONAMENTO FÍSICO ---

        baseDeConhecimento.add(new Regra("Condicionamento", "Iniciante", true,
            "Caminhada plana contínua, ciclismo horizontal e alongamentos dinâmicos.",
            "Iniciantes com restrição buscando condicionamento devem focar na eficiência cardíaca de base. Exercícios cíclicos de baixa intensidade melhoram o VO2 máximo sem gerar picos de sobrecarga mecânica."));
        
        baseDeConhecimento.add(new Regra("Condicionamento", "Iniciante", false,
            "Trote intermitente (Caminha/Corre), calistenia básica e circuitos de endurance.",
            "Sendo iniciante e sem restrições, o estímulo misto (aeróbico e muscular leve) promove adaptações cardiorrespiratórias rápidas e melhora a resistência muscular localizada global."));
        
        baseDeConhecimento.add(new Regra("Condicionamento", "Intermediário", true,
            "Uso de Remo seco (ergômetro), natação e circuitos funcionais sem impacto.",
            "Para intermediários com restrição, o remo e a natação são excelentes para o condicionamento cardio, pois exigem alta demanda de oxigênio sem gerar o impacto repetitivo do solo nas articulações."));
        
        baseDeConhecimento.add(new Regra("Condicionamento", "Intermediário", false,
            "Corrida de rua ritmada, circuitos funcionais de alta intensidade e ciclismo outdoor.",
            "Como intermediário sem restrições, seu plano foca na expansão do limiar de lactato. Circuitos dinâmicos elevam a frequência cardíaca e treinam o corpo a se recuperar mais rápido de esforços intensos."));
        
        baseDeConhecimento.add(new Regra("Condicionamento", "Avançado", true,
            "Remo de alta intensidade, ciclismo indoor avançado e simulação de circuitos cardioprotetores.",
            "Sendo avançado com restrição, desafiamos seu excelente sistema cardiovascular através de treinos de potência em ambientes controlados, garantindo rendimento de elite sem agravar limitações."));
        
        baseDeConhecimento.add(new Regra("Condicionamento", "Avançado", false,
            "Treinos de Sprint intervalados, pliometria (saltos) e treinos de potência metabólica total.",
            "Para o nível avançado e livre de restrições, o foco é a máxima performance esportiva, utilizando pliometria e tiros de velocidade para otimizar a potência anaeróbica alática e a agilidade."));

        // --- FLEXIBILIDADE ---

        baseDeConhecimento.add(new Regra("Flexibilidade", "Iniciante", true,
            "Alongamentos estáticos passivos de grandes cadeias musculares e sessões leves de Fisiopilates.",
            "Para iniciantes buscando flexibilidade com alguma restrição física, são indicados alongamentos estáticos suaves e controlados para aliviar tensões e aumentar a amplitude de movimento protetiva."));
        
        baseDeConhecimento.add(new Regra("Flexibilidade", "Iniciante", false,
            "Alongamentos estáticos globais e posturas básicas de Yoga.",
            "Como iniciante sem restrições, o foco está no relaxamento neuromuscular e alongamento de cadeias encurtadas (como posterior de coxa e coluna), melhorando a postura geral diária."));
        
        baseDeConhecimento.add(new Regra("Flexibilidade", "Intermediário", true,
            "Pilates clássico adaptado e técnicas de facilitação neuromuscular (FNP) com auxílio.",
            "Sendo intermediário com restrição, o método de facilitação ajuda a vencer o reflexo de estiramento com segurança, promovendo ganho de mobilidade respeitando as barreiras biológicas do seu corpo."));
        
        baseDeConhecimento.add(new Regra("Flexibilidade", "Intermediário", false,
            "Yoga dinâmica (estilo Vinyasa) e rotinas de alongamentos ativos-dinâmicos.",
            "No nível intermediário e livre, integramos força excêntrica e flexibilidade. O movimento dinâmico melhora a lubrificação articular e a elasticidade de tendões e fáscias musculares."));
        
        baseDeConhecimento.add(new Regra("Flexibilidade", "Avançado", true,
            "Técnicas de alongamento por contração-relaxamento assistido e Yoga terapêutica avançada.",
            "Para o perfil avançado com restrição, trabalhamos no limite seguro da amplitude de movimento, utilizando controle respiratório profundo para inibir o tônus protetivo gerado pela restrição física."));
        
        baseDeConhecimento.add(new Regra("Flexibilidade", "Avançado", false,
            "Yoga avançada (Asanas complexos) e rotinas severas de flexibilidade (estilo ginástica artística).",
            "Sendo avançado sem restrições, o treinamento visa a flexibilidade extrema e a hipermobilidade controlada, promovendo uma descompressão articular completa e elasticidade muscular total."));
    }

    public void executarInferencia(String objetivo, String nivel, boolean restricao) {
        System.out.println("\n========================================================");
        System.out.println("          PROCESSANDO REGRAS DE INFERÊNCIA...           ");
        System.out.println("========================================================");
        
        boolean regraDisparada = false;

        for (Regra regra : baseDeConhecimento) {
            if (regra.avaliar(objetivo, nivel, restricao)) {
                System.out.println("\n[SISTEMA ESPECIALISTA INFORMA]:");
                System.out.println("\n-> INDICAÇÃO DE EXERCÍCIOS:");
                System.out.println(regra.recomendacao);
                System.out.println("\n-> JUSTIFICATIVA FISIOLÓGICA:");
                System.out.println(regra.justificativa);
                regraDisparada = true;
                break;
            }
        }

        if (!regraDisparada) {
            System.out.println("\n[ERRO]: Não foi possível encontrar uma recomendação para a combinação de fatos fornecida.");
        }
        System.out.println("========================================================\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaEspecialistaExercicios sistema = new SistemaEspecialistaExercicios();

        System.out.println("========================================================");
        System.out.println("     SISTEMA ESPECIALISTA - PRESCRIÇÃO DE EXERCÍCIOS    ");
        System.out.println("========================================================");

        System.out.println("\nSelecione o seu OBJETIVO PRINCIPAL:\n");
        System.out.println("1 - Emagrecimento");
        System.out.println("2 - Ganho de Massa");
        System.out.println("3 - Condicionamento");
        System.out.println("4 - Flexibilidade");
        System.out.print("\nDigite o número correspondente: ");
        int opcaoObjetivo = scanner.nextInt();
        
        String objetivoFato = "";
        switch (opcaoObjetivo) {
            case 1: objetivoFato = "Emagrecimento";
                break;
            case 2: objetivoFato = "Ganho de Massa";
                break;
            case 3: objetivoFato = "Condicionamento";
                break;
            case 4: objetivoFato = "Flexibilidade";
                break;
            default:
                System.out.println("Opção inválida. Encerrando.");
                return;
        }

        System.out.println("\nSelecione o seu NÍVEL DE EXPERIÊNCIA:\n");
        System.out.println("1 - Iniciante");
        System.out.println("2 - Intermediário");
        System.out.println("3 - Avançado");
        System.out.print("/n Digite o número correspondente: ");
        int opcaoNivel = scanner.nextInt();
        
        String nivelFato = "";
        switch (opcaoNivel) {
            case 1: nivelFato = "Iniciante";
                break;
            case 2: nivelFato = "Intermediário";
                break;
            case 3: nivelFato = "Avançado";
                break;
            default:
                System.out.println("Opção inválida. Encerrando.");
                return;
        }

        System.out.println("\nVocê possui alguma RESTRIÇÃO FÍSICA ou LESÃO? (Ex: problemas de joelho, coluna, etc.)");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        System.out.print("\nDigite o número correspondente: ");
        int opcaoRestricao = scanner.nextInt();
        
        boolean restricaoFato = false;
        if (opcaoRestricao == 1) {
            restricaoFato = true;
        } else if (opcaoRestricao != 2) {
            System.out.println("Opção inválida. Encerrando.");
            return;
        }

        sistema.executarInferencia(objetivoFato, nivelFato, restricaoFato);

        scanner.close();
    }
        

}
