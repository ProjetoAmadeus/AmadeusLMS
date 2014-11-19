package br.ufpe.cin.amadeus.amadeus_web.util;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.io.GraphMLWriter;

public class SocialInteractionMethods {
	/*
	 * CriaÃ§Ã£o do grafo dirigido a partir das interaÃ§Ãµes sociais nas ferramentas de comunicaÃ§Ã£o Amadeus
	 */
	public DirectedSparseMultigraph<String,String> generateGhaph(ArrayList<String> actors, MultiMap interactions){
		
		DirectedSparseMultigraph<String, String> graph = new DirectedSparseMultigraph<String,String>();
		ArrayList<String> edges;
		
		for (String i:actors){
			graph.addVertex(i);
		}
		
		for (String j:actors){
			edges = (ArrayList<String>)interactions.get(j);
			if (edges != null){
				for (String i:edges){
					graph.addEdge("edge" + j + "" + i, j, i, EdgeType.DIRECTED);
				}
			}
			
		}
	    
		//System.out.println(graph.getEdgeCount());
		//System.out.println(graph);
		return graph;
	}
	
	
	/*
	 * MÃ©todo que calcula a coesÃ£o do grupo da rede social atravÃ©s da aplicaÃ§Ã£o
	 * da mÃ©trica densidade de AnÃ¡lise de Redes Sociais
	 */
	
	public String cohesionGroup(ArrayList<String> actors, MultiMap interactions){
		
		DirectedSparseMultigraph<String, String> graph = this.generateGhaph(actors, interactions);
		
		Collection<String> edges = graph.getEdges();
		
		double numArcs = (double)edges.size();
		
		double numActors = (double)actors.size();
		
		double density = (numArcs / (numActors * (numActors - 1)));
		
		Double density1 = new Double(density);
		
		double p = Math.pow(10, 4);  
		
		Double densityFormated = Math.round(density1 * p) / p;
		
		// Save graph
		
		this.saveGraph(graph);
		
		return this.reportCohesion(densityFormated.toString());
		
	}
	
	/*
	 * MÃ©todo que gera o relatÃ³rio que serÃ¡ oferecido ao professor sobre a coesÃ£o dos alunos da turma
	 */
	
	public String reportCohesion(String density){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String data = dateFormat.format(date);
		
		String head = "**Grau de Coesão**        " + data + "\n"+ "\n"+ "É uma medida usada para avaliar o quão coeso está um grupo social. Também indica o nível de interatividade do grupo e oferece ao professor um diagnóstico da coesividade do grupo de alunos. Oferece também uma indicação do nível geral de engajamento do grupo social.";
		
		String result = "Grau de Coesão: " + density;
		
		double densityDouble = Double.parseDouble(density); 
		
		String diagnosis = this.cohesionScale(densityDouble);
		
		String intervention = "Se o grau de coesão apresenta nenhuma coesão ou fraca coesão, o professor deve promover mais colaboração nas ferramentas de comunicação. As sugestões propostas nas métricas de comportamento social individuais visibilidade, isolamento e engajamento,  quando aplicadas, devem promover um aumento da coesão do grupo social. Professores devem regularmente monitorar esta métrica e comparar os resultados considerando o tempo.";
		
		return head + "\n" +"\n" + result + "\n" +"\n" + "Diagnóstico: " + diagnosis + "\n" + "\n" + "Sugestões: " + "\n" + intervention  ;
		
	}
	
	/*
	 * MÃ©todo que retorna o grau de coesÃ£o do grupo baseado na densidade calculada
	 */
	public String cohesionScale(double density){
		
		if (density == 0.0){
			return "Grupo não coeso";
		} else if (density > 0.0 && density <= 0.2){
			return "Grupo fracamente coeso";
		} else if (density > 0.2 && density <= 0.5){
			return "Grupo coeso";
		} else if (density > 0.5 && density <= 1){
			return "Grupo fortemente coeso";
		} else {
			return "Grupo completamente coeso";
		}
		
	}
	

	
	/*
	 * MÃ©todo, invocado pela interface de monitoramento de interaÃ§Ãµes sociais, que calcula e analisa
	 * o prestÃ­gio dos estudantes de uma rede social
	 */
	public String prestigePerStudent(ArrayList<String> actors, MultiMap interactions){
		
		
		Map<String,Integer> indegreeValues = this.prestigeStudentsValued(actors, interactions);
		
		
		Map<String,Integer> sortedMap = this.sortMap(indegreeValues);
		
		// Save graph
		
		DirectedSparseMultigraph<String, String> graph = this.generateGhaph(actors, interactions);
		
		this.saveGraph(graph);
		
		return this.reportPrestige(sortedMap);
	
	}
	
	
	/*
	 * MÃ©todo que gera o relatÃ³rio que serÃ¡ oferecido ao professor sobre o prestÃ­gio dos alunos do curso
	 */
	
	public String reportPrestige(Map<String,Integer> indegreeValue){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String data = dateFormat.format(date);
		
		String head = "**Grau de Prestígio**          "+ data  +  "\n"+ "\n"+ "É uma medida usada para quantificar o prestígio dos estudantes perante seus colegas, baseado nas respostas as suas mensagens e comentários dos seus posts. Essa métrica oferece ao professor uma forma de indentificar o interesse dos colegas da turma em cada estudante.";
		
		String result = "Grau de prestígio: ";
		
		// Print Map (Strudent - Prestige)
		
		Collection<String> unsorted = indegreeValue.keySet();
		
		String[] array = (String[]) unsorted.toArray(new String[unsorted.size()]);
		
		for (String key : array) {
			
			result += "\n"+ key + " - "+ indegreeValue.get(key) + " ";
			
		}
		
		//Arredonda para cima
		int qtdInteractions = (int)Math.ceil((20 * unsorted.size())/100.0);
		
		
		String diagnosis = "Os estudantes "+ this.bigger(array, qtdInteractions) + " tem um maior prestígio em relação a seus colegas, enquanto os estudantes "+ this.smaller(array, qtdInteractions) + " tem menos prestígio em relação aos seus colegas" ;
		
		String intervention = "O prestígio em grupos sociais surge através da quantidade e qualidade das discussões propostas e comentários realizados. Abordar os estudantes com menor prestígio e sugerir que os mesmos criem novos posts tratando de assuntos relacionados aos estudados. Propor que sempre responda os comentários aos posts criados inicialmente, apresentando respostas, comportando-se como um intermediador.";
		
		return head + "\n" +"\n" + result + "\n" +"\n" + "Diagnóstico: " + "\n"  + diagnosis + "\n" + "\n" + "Sugestões:" + "\n" + intervention  ;
		
	}
	
	
	/*
	 * MÃ©todo que recebe um MultiMap com as interacoes sociais dos estudantes e retorna o prestÃ­gio
	 * de cada estudante baseado na quantidade de interaÃ§Ãµes que finalizam no estudante.SÃ£o consideradas
	 * as interaÃ§Ãµes repetidas entre os estudantes, diferentemente do mÃ©todo indegree da classe DirectedSparseMultigraph
	 */
	public Map<String,Integer> prestigeStudentsValued(ArrayList<String> actors, MultiMap interactions){
		
		Map<String,Integer> prestige = new HashMap<String,Integer>();
		int cont = 0;
		
		Collection<String> col = interactions.values();
		ArrayList<String> interactionsEnd = new ArrayList<String>(col);
		
		for (String i:actors){
			for (String j:interactionsEnd){
					if (i.equals(j)){
						cont++;
					}
			}
			prestige.put(i, cont);
			cont = 0;
		}
		
		return prestige;
		
	}
	
	/*
	 * MÃ©todo, invocado pela interface de monitoramento de interaÃ§Ãµes sociais, que calcula e analisa
	 * o engajamento dos estudantes de uma rede social
	 */
	public String engagementPerStudent(ArrayList<String> actors, MultiMap interactions){
		
		Map<String,Integer> outdegreeValues = this.engagementStudentsValued(actors, interactions);
		
		Map<String,Integer> sortedMap = this.sortMap(outdegreeValues);
		
		// Save graph
		
		DirectedSparseMultigraph<String, String> graph = this.generateGhaph(actors, interactions);
				
		this.saveGraph(graph);
		
		return this.reportEngagement(sortedMap);
		
	
	}
	
	/*
	 * MÃ©todo que recebe um MultiMap com as interacoes sociais dos estudantes e retorna o grau de engajamento
	 * de cada estudante baseado na quantidade de interaÃ§Ãµes que comeÃ§am com o estudante.SÃ£o consideradas
	 * as interaÃ§Ãµes repetidas entre os estudantes, diferentemente do mÃ©todo outdegree da classe DirectedSparseMultigraph
	 */
	public Map<String,Integer> engagementStudentsValued(ArrayList<String> actors, MultiMap interac){
		
		MultiValueMap interactions = (MultiValueMap)interac;
		Map<String,Integer> engagement = new HashMap<String,Integer>();
		int cont = 0;
		
		for (String i:actors){
			cont = interactions.size(i);
			engagement.put(i, cont);
			cont = 0;
			//System.out.println("Engajamento: " + engagement);
		}
		
		return engagement;
		
	}
	
	/*
	 * MÃ©todo que gera o relatÃ³rio que serÃ¡ oferecido ao professor sobre o prestÃ­gio dos alunos do curso
	 */
	
	public String reportEngagement(Map<String,Integer> indegreeValue){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String data = dateFormat.format(date);
		
		String head = "**Grau de Engajamento**         "+ data + "\n"+ "\n"+ "O engajamento de um estudante corresponde ao número de interações sociais nas ferramentas de comunicação e descreve o quão ativo e central é ou está o estudante perante o grupo. Estudantes engajados são canais importantes por onde fluem as informações.  ";
		
		String result = "Grau de Engajamento: ";
		
		Collection<String> unsorted = indegreeValue.keySet();
		
		String[] array = (String[]) unsorted.toArray(new String[unsorted.size()]);
		
		for (String key : array) {
			
			result += "\n"+ key + " - "+ indegreeValue.get(key) + " ";
			
		}
		
		//Arredonda para cima
		int qtdInteractions = (int)Math.ceil((20 * unsorted.size())/100.0);
		
		
		String diagnosis = "Os estudantes "+ this.bigger(array, qtdInteractions) + " tem um maior engajamento em relação aos seus colegas, enquanto "+ this.smaller(array, qtdInteractions) + " têm um menor engajamento em relação aos seus colegas" ;
		
		String intervention = "Para aumentar o engajamento, o professor deve encorajar atitudes ativas dos estudantes, sempre instigando-os, especialmente os que possuem um baixo grau de engajamento. Pensando nos estudantes de forma geral, o professor deve propor atividades e trabalhos desafiadores, que necessitem pesquisas fora do Amadeus e que promovam discussões, reflexões e construção do conhecimento colaborativamente entre os estudantes do curso. Como ações diretas, enviar mensagens para os estudantes com baixo grau de engajamento e pedir que eles teçam suas opiniões, sugiram soluções ou indiquem fontes de pesquisa sobre determinado assunto nas ferramentas de comunicação do Amadeus e assim tornem-se mais centrais e visíveis aos outros estudantes. Iniciar discussõees sobre assuntos indiretamente relacionados ao assunto estudado, ensejando que os estudantes se identifiquem com interesses pessoais e profissionais e naturalmente se envolvam mais.";
		
		return head + "\n" +"\n" + result + "\n" +"\n" + "Diagnóstico: " + "\n"  + diagnosis + "\n" + "\n" + "Sugestões:" + "\n" + intervention  ;
		
	}
	
	/*
	 * MÃ©todo, invocado pela interface de monitoramento de interaÃ§Ãµes sociais, que calcula e analisa
	 * a visibilidade ou amplitude da interatividade dos estudantes de uma rede social
	 */
	public String visibilityPerStudent(ArrayList<String> actors, MultiMap interactions){
		
		Map<String,Integer> outdegreeValues = this.outdegree(actors, interactions);
		
		
		Map<String,Integer> sortedMap = this.sortMap(outdegreeValues);
		
		
		Map<String,Double> visibilityResult = centralityPerStudent(sortedMap);
			
		
		return this.reportVisibility(visibilityResult);
		
	
	}
	
	/*
	 * MÃ©todo que recebe o outdegree de todos os estudantes na forma de um Map e retorna, tambÃ©m atravÃ©s,
	 * de um Map o grau de centralidade de cada estudante do curso.
	 */
	public Map<String,Double> centralityPerStudent(Map<String,Integer> outdegree){
			
		ArrayList<Double> centralities = new ArrayList<Double>();
		
		ArrayList<Integer> outdegrees = new ArrayList<Integer>(outdegree.values());
		
		Map<String,Double> centralityList = new LinkedHashMap<String,Double>();
		
		Collection<String> keys = outdegree.keySet();
		ArrayList<String> keysList = new ArrayList<String>(keys);
		
		int groupSize = outdegrees.size() - 1;
		double groupSizeDouble = (double)groupSize;
		
		//System.out.println(groupSizeDouble);
		
		double p;
		Double centralityFormated;
		double outdegreeValues;
		int cont = 0;
		for (int i:outdegrees){
			outdegreeValues = (double)outdegrees.get(cont)/groupSizeDouble;
			p = Math.pow(10, 4);  
			centralityFormated = Math.round(outdegreeValues * p) / p;
			//System.out.println(outdegreeValues);
			centralities.add(centralityFormated);
			centralityList.put(keysList.get(cont), centralities.get(cont));
			cont++;
		}
		
		return centralityList;
		
	}
	
	/*
	 * MÃ©todo que gera o relatÃ³rio que serÃ¡ oferecido ao professor sobre o visibilidade (nÃ­vel de interatividade) dos alunos do curso
	 */
	
	public String reportVisibility(Map<String,Double> indegreeValue){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String data = dateFormat.format(date);
		
		String head = "**Grau de Visibilidade**       "+ data + "\n"+ "\n"+ "A visibilidade é uma métrica usada para medir a quantidade de interações sociais oriundas e que terminam em cada estudante dentro de uma rede social (normalizada). Esta métrica fornece informações acerca da posição do estudante em relação a troca de informações e comunicação dentro do grupo. Através dela pode-se perceber se as interações estão centralizadas em um pequeno grupo de estudantes ou distribuídas mais uniformemente. ";
		
		String result = "Grau de Visibilidade: ";
		
		Collection<String> unsorted = indegreeValue.keySet();
		
		String[] array = (String[]) unsorted.toArray(new String[unsorted.size()]);
		
		for (String key : array) {
			
			result += "\n"+ key + " - "+ indegreeValue.get(key) + " ";
			
		}
		
		//Arredonda para cima
		int qtdInteractions = (int)Math.ceil((20 * unsorted.size())/100.0);
		
		
		String diagnosis = "Os estudantes "+ this.bigger(array, qtdInteractions) + " tem maior grau de visibilidade perante seus colegas, enquanto "+ this.smaller(array, qtdInteractions) + " tem menor grau de visibilidade perante seus colegas." ;
		
		String intervention = "Para aumentar a visibilidade o professor deve encorajar, especialmente estudantes com baixo grau de visibilidade, a utilização das diferentes ferramentas de comunicação disponíveis no Amadeus. Sugerir aos alunos com baixa visibilidade a criação de novos posts nos fóruns ao invés de somente comentar posts existentes, isso fará com que o mesmo atinja uma maior visibilidade. Citar alunos ou comentários de alunos com baixa visibilidade nos posts das diferentes ferramentas de comunicação de modo a tornar esses estudantes mais visíveis. ";
		
		return head + "\n" +"\n" + result + "\n" +"\n" + "Diagnóstico: " + "\n"  + diagnosis + "\n" + "\n" + "Sugestões:" + "\n" + intervention  ;
		
	}
	
	/*
	 * MÃ©todo, invocado pela interface de monitoramento de interaÃ§Ãµes sociais, que calcula e analisa
	 * a heterogeneidade de um grupo de estudantes de uma rede social.
	 */
	public String heterogeneityGroup(ArrayList<String> actors, MultiMap interactions){
		
		Map<String,Integer> outdegreeValues = this.outdegree(actors, interactions);
		
		
		Map<String,Integer> sortedMap = this.sortMap(outdegreeValues);
		
		
		Map<String,Double> visibilityResult = centralityPerStudent(sortedMap);
		
		
		Map<String,Double> biggerCentralityMinusCentralities =  this.calculateDifferenceBetweenBiggerAndOthers(visibilityResult);
		
		
		Double heterogeneityDegree = this.calculateHeterogeneityDegree(biggerCentralityMinusCentralities);
		
		
		return this.reportHeterogeneity(heterogeneityDegree);
		
	
	}
	
	/*
	 * MÃ©todo que calcula a diferenÃ§a do estudante com maior centralidade da centralidade de todos os estudantes, armazenando
	 * em um Map, que Ã© retornado. Parte do cÃ¡lculo do grau de heterogeneidade do grupo.
	 */
	public Map<String,Double> calculateDifferenceBetweenBiggerAndOthers(Map<String,Double> visibilityDegree){
		
		ArrayList<Double> centralities = new ArrayList<Double>();
		
		ArrayList<Double> biggerMinusCentralities = new ArrayList<Double>(visibilityDegree.values());
		
		Map<String,Double> centralityList = new LinkedHashMap<String,Double>();
		
		Double bigCentrality = biggerMinusCentralities.get(0);
		
		Collection<String> keys = visibilityDegree.keySet();
		
		ArrayList<String> keysList = new ArrayList<String>(keys);
		
		double centralityValues;
		int cont = 0;
		for (double i:biggerMinusCentralities){
			centralityValues = bigCentrality - biggerMinusCentralities.get(cont);
			centralities.add(centralityValues);
			centralityList.put(keysList.get(cont), centralities.get(cont));
			cont++;
		}
		
		return centralityList;
		
	}
	
	/*
	 * MÃ©todo que calcula o grau de heterogeneidade do grupo.
	 */
	public Double calculateHeterogeneityDegree(Map<String,Double> visibilityDegree){
		
		ArrayList<Double> biggerMinusCentralities = new ArrayList<Double>(visibilityDegree.values());
		
		int groupSize = visibilityDegree.size() - 1;
		double groupSizeDouble = (double)groupSize;
		
		Double sumCentralities = biggerMinusCentralities.get(0);
		
		int cont = 0;
		for (double i:biggerMinusCentralities){
			sumCentralities += biggerMinusCentralities.get(cont++);
		}
		
		return sumCentralities/(Math.pow(groupSizeDouble, 2));
		
	}
	
	/*
	 * MÃ©todo que gera o relatÃ³rio que serÃ¡ oferecido ao professor sobre a coesÃ£o dos alunos da turma
	 */
	
	public String reportHeterogeneity(Double heterogeneity){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String data = dateFormat.format(date);
		
		String head = "**Grau de Heterogeneidade**        "+ data + "\n"+ "\n"+ "É uma medida usada para descobrir o grau de variabilidade das interações dos estudantes em um curso. Oferece ao professor uma indicação da heterogeneidade do grupo, baseado nas interações sociais, através de uma única medida.";
		
		// Formatar o grau de heterogeneidade
		
		double p = Math.pow(10, 4);  
		
		Double heterogeneityFormated = Math.round(heterogeneity * p) / p; 
        
        String result = "Grau de Heterogeneidade: " + heterogeneityFormated;
		
		
		String diagnosis = "O grau de heterogeneidade varia de 0 a 1, onde quanto mais próximo de zero mais heterogeneo é ou está o grupo. Se o grau de heterogeneidade se aproxima de 1, significa que poucos estudantes são centrais, enquanto a maioria estão localizados estruturalmente de forma periférica na rede social.";
		
		String intervention = "O professor deve promover, de forma geral, mais colaboração nas ferramentas de comunicação do Amadeus. As sugestões propostas nas métricas de comportamento social individuais visibilidade, isolamento e engajamento,  quando aplicadas, devem promover um aumento da heterogeneidade do grupo social. Professores devem regularmente monitorar esta métrica e comparar os resultados considerando o tempo.";
		
		return head + "\n" +"\n" + result + "\n" +"\n" + "Diagnóstico: " + "\n"  + diagnosis + "\n" + "\n" + "Sugestões:" + "\n" + intervention  ;
		
	}
	
	/*
	 * MÃ©todo, invocado pela interface de monitoramento de interaÃ§Ãµes sociais, que calcula e analisa
	 * o grau de intermediaÃ§Ã£o da informaÃ§Ã£o dos estudantes (individualmente) de uma rede social
	 */
	public String informationIntermediationPerStudent(ArrayList<String> actors, MultiMap interactions){
		
		int sizeGroup = actors.size();
		
		Map<String,Double> betweenness = new HashMap<String,Double>();
		
		Map<String,Double> betweennessSorted = new HashMap<String,Double>();
		
		DirectedSparseMultigraph<String, String> graph = this.generateGhaph(actors, interactions);
		
		BetweennessCentrality ranker = new BetweennessCentrality(graph);
		
		ranker.setRemoveRankScoresOnFinalize(false);
		
		ranker.evaluate();
		
		//ranker.printRankings(true, false);
		
		double bet, finalBet, p;
		Double intermediationFormated;
		
		for (String actor:actors){
			
			bet = ranker.getVertexRankScore(actor);
			finalBet = bet/((sizeGroup-2) * (sizeGroup-1));
			p = Math.pow(10, 4);  
			
			intermediationFormated = Math.round(finalBet * p) / p; 
			betweenness.put(actor, intermediationFormated);
			
		}
	
		betweennessSorted = this.sortMapDouble(betweenness);
		
		// Save graph
		
		this.saveGraph(graph);
		
		return this.reportIntermediationInformation(betweennessSorted);
	
	}
	
	/*
	 * MÃ©todo que gera o relatÃ³rio que serÃ¡ oferecido ao professor sobre o visibilidade (nÃ­vel de interatividade) dos alunos do curso
	 */
	
	public String reportIntermediationInformation(Map<String,Double> indegreeValue){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String data = dateFormat.format(date);
		
		String head = "**Grau de Intermediação da Informação**     "+ data  + "\n"+ "\n"+ "É uma medida usada para determinar a quantidade de caminhos mínimos entre todos os estudantes, que passam por cada estudante especificamente. No contexto de interação social entre estudantes, o estudante pode até não ter um alto grau de interação direta com seus colegas ou mesmo estabelecer laços fortes dentro da rede social, mas pode ser primordial para mediar a informação entre estudantes não adjacentes. ";
		
		String result = "Grau de Intermediação da Informação: ";
		
		Collection<String> unsorted = indegreeValue.keySet();
		
		String[] array = (String[]) unsorted.toArray(new String[unsorted.size()]);
		
		for (String key : array) {
			
			result += "\n"+ key + " - "+ indegreeValue.get(key) + " ";
			
		}
		
		//Arredonda para cima
		int qtdInteractions = (int)Math.ceil((20 * unsorted.size())/100.0);
		
		
		String diagnosis = "Os estudantes "+ this.bigger(array, qtdInteractions) + " têm maiores graus de intermediação de informação perante seus colegas, enquanto os seus colegas "+ this.smaller(array, qtdInteractions) + " tem menores graus de intermediação perante seus colegas. Esta métrica oferece ao professor a identificação dos estudantes, que caso se ausentem do grupo, quebram o ciclo de transmissão de informações, levando o grupo a ser dividido em subgrupos isolados." ;
		
		String intervention = "Duas ações podem e devem ser tomadas, a primeira deve-se garantir que os estudantes intermediadores de informação continuem assim e não se isolem gerando formação de subgrupos, a outra é buscar um aumento da quantidade de estudantes intermediadores de informação. Para manter os estudantes já nesta posição, o professor pode visualizar no grafo gerado pelo Amadeus os subgrupos cujo intermediário das informações é o estudante e sugerir atividades em pares unindo o estudante em questão e estudantes de diferentes subgrupos ou isolados. Para que novos estudantes passem a integrar o grupo de estudantes com alto grau de intermediação da informação, além da motivação geral nas ferramentas de comunicação do Amadeus, empreender uma atenção especial aos estudantes com baixo ou  nenhum grau de intermadiação da informação, utilizando a ferramenta de mensagens diretas.";
		
		return head + "\n" +"\n" + result + "\n" +"\n" + "Diagnóstico: " + "\n"  + diagnosis + "\n" + "\n" + "Sugestões:" + "\n" + intervention  ;
		
	}
	
	/*
	 * MÃ©todo, invocado pela interface de monitoramento de interaÃ§Ãµes sociais, que calcula e analisa
	 * o grau de intermediaÃ§Ã£o da informaÃ§Ã£o dos estudantes (individualmente) de uma rede social
	 */
	public String isolationPerStudent(ArrayList<String> actors, MultiMap interactions){
		
		int sizeGroup = actors.size();
		
		Map<String,Double> closeness = new HashMap<String,Double>();
		
		Map<String,Double> closenessSorted = new HashMap<String,Double>();
		
		DirectedSparseMultigraph<String, String> graph = this.generateGhaph(actors, interactions);
		
		System.out.println("Grafo: " + graph);
		
		ClosenessCentrality ranker = new ClosenessCentrality(graph);
		
		
		double bet, finalBet, p;
		Double intermediationFormated;
		
		for (String actor:actors){
			
			bet = ranker.getVertexScore(actor);
			p = Math.pow(10, 4);  
			
			intermediationFormated = Math.round(bet * p) / p; 
			closeness.put(actor, intermediationFormated);
			
		}
	
		closenessSorted = this.sortMapDouble(closeness);
		
		// Save graph
		
		this.saveGraph(graph);
		
		return this.reportIsolation(closenessSorted);
	
	}
	
	/*
	 * MÃ©todo que gera o relatÃ³rio que serÃ¡ oferecido ao professor sobre o visibilidade (nÃ­vel de interatividade) dos alunos do curso
	 */
	
	public String reportIsolation (Map<String,Double> indegreeValue){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String data = dateFormat.format(date);
		
		String head = "**Grau de Isolamento**      "+ data + "\n"+ "\n"+ " É uma medida que determina a menor distância (geodésica) entre cada estudante e todos os outros colegas do curso, ou seja, o quão próximo ou distante cada estudante está dos demais. No contexto da interação social entre estudantes, esta métrica oferece ao professor uma indicação de possíveis estudantes isolados.";
		
		String result = "Grau de Isolamento: ";
		
		Collection<String> unsorted = indegreeValue.keySet();
		
		String[] array = (String[]) unsorted.toArray(new String[unsorted.size()]);
		
		for (String key : array) {
			
			result += "\n"+ key + " - "+ indegreeValue.get(key) + " ";
			
		}
		
		//Arredonda para cima
		int qtdInteractions = (int)Math.ceil((20 * unsorted.size())/100.0);
		
		
		String diagnosis = "Os estudantes "+ this.bigger(array, qtdInteractions) + " têm um menor grau de isolamento em relação a seus colegas, enquanto os estudantes "+ this.smaller(array, qtdInteractions) + " tem um maior grau de isolamento, ou seja, estão mais próximos dos seus colegas. É possível identificar dificuldade de comunicação entre estudantes causadas pela total ou parcial isolamento do grupo." ;
		
		String intervention = "Iniciar discussões a partir de estudantes com menor grau de isolamento, uma vez que estando mais próximos dos outros estudantes, essas informações atingirão mais rapidamente o grupo como um todo. Sugerir trabalhos em grupo agrupando estudantes com alto e baixo graus de isolamento. Resgatar estudantes completamente isolados através de mensagens individuais e sugestão de trabalhos diferenciados.";
		
		return head + "\n" +"\n" + result + "\n" +"\n" + "Diagnóstico: " + "\n"  + diagnosis + "\n" + "\n" + "Sugestões:" + "\n" + intervention  ;
		
	}
	
	/*
	 * MÃ©todo, que deve ser invocado por todas os comportamentos (mÃ©tricas) relacionadas a cada ator
	 * que retorna na forma de uma String, os nomes dos estudantes (%) com maior grau
	 */
	public String bigger(String[] array, int size){
		
		String list = " ";
		
		for (int i = 0; i < size; i++){
			list += array[i] + ", ";
		}

		
		return list;
		
	}
	
	/*
	 * MÃ©todo, que deve ser invocado por todas os comportamentos (mÃ©tricas) relacionadas a cada ator
	 * que retorna na forma de uma String, os nomes dos estudantes (%) com maior grau
	 */
	public String smaller(String[] array, int size){
		
		String list = " ";
		
		for (int i = array.length-1; i >= (array.length - size); i--){
			list += array[i] + ", ";
		}

		
		return list;
		
	}
	
	/*
	 * Ordena os estudantes, em ordem decrescente, baseado em alguma mÃ©trica de AnÃ¡lise de Redes Sociais. Este mÃ©todo serÃ¡
	 * chamado por todas as mÃ©tricas representadas por Integer no momento de gerar o relatÃ³rio.
	 */
	
	public Map<String,Integer> sortMap(Map<String,Integer> indegreeValue){
	
		Map<String,Integer> newIndegreeValue = new LinkedHashMap<String,Integer>();
		// Local variables
		Integer currentValue;
		int cont;
		String next;
		String bigKey;
		Integer bigValue;
		Collection col;
		ArrayList<Integer> noSortedValues;
		Collection<String> unsortedKeys;
		Iterator it;
		String[] array;
		int sizeMap = indegreeValue.size();
		
		for (int i = 0; i< sizeMap; i++){
			
				col = indegreeValue.values();
				
				noSortedValues = new ArrayList<Integer>(col);
				
				unsortedKeys = indegreeValue.keySet();
				
				it = unsortedKeys.iterator();
			
				array = (String[]) unsortedKeys.toArray(new String[unsortedKeys.size()]);		
				
				//Encontrar o maior valor
				cont = 0;
				next= (String)it.next();
				bigKey = next;
				//bigIndex = cont;
				bigValue = noSortedValues.get(cont);
				while (it.hasNext()){
					cont++;
					next = (String)it.next();
					//currentValue = indegreeValue.get(noSortedValues.get(cont));
					currentValue = noSortedValues.get(cont);
					if (currentValue.intValue() >= bigValue){
					    //bigIndex = cont;
						bigValue = currentValue;
						bigKey = next;
					}
				}
				
				indegreeValue.remove(bigKey);
				newIndegreeValue.put(bigKey, bigValue);				
		
			}
			
			return newIndegreeValue;
		
	}
	
	/*
	 * Ordena os estudantes, em ordem decrescente, baseado em alguma mÃ©trica de AnÃ¡lise de Redes Sociais. Este mÃ©todo serÃ¡
	 * chamado por todas as mÃ©tricas representadas por Double no momento de gerar o relatÃ³rio.
	 */
	
	public Map<String,Double> sortMapDouble(Map<String,Double> indegreeValue){
		
		Map<String,Double> newIndegreeValue = new LinkedHashMap<String,Double>();
		// Local variables
		Double currentValue;
		int cont;
		String next;
		String bigKey;
		Double bigValue;
		Collection col;
		ArrayList<Double> noSortedValues;
		Collection<String> unsortedKeys;
		Iterator it;
		String[] array;
		int sizeMap = indegreeValue.size();
		
		for (int i = 0; i< sizeMap; i++){
			
				col = indegreeValue.values();
				
				noSortedValues = new ArrayList<Double>(col);
				
				unsortedKeys = indegreeValue.keySet();
				
				it = unsortedKeys.iterator();
			
				array = (String[]) unsortedKeys.toArray(new String[unsortedKeys.size()]);		
				
				//Encontrar o maior valor
				cont = 0;
				next= (String)it.next();
				bigKey = next;
				//bigIndex = cont;
				bigValue = noSortedValues.get(cont);
				while (it.hasNext()){
					cont++;
					next = (String)it.next();
					//currentValue = indegreeValue.get(noSortedValues.get(cont));
					currentValue = noSortedValues.get(cont);
					if (currentValue >= bigValue){
					    //bigIndex = cont;
						bigValue = currentValue;
						bigKey = next;
					}
				}
				
				indegreeValue.remove(bigKey);
				newIndegreeValue.put(bigKey, bigValue);				
		
			}
			
			return newIndegreeValue;
		
	}
	
	/*
	 * MÃ©todo que calcula o Ã­ndice indegree de cada estudante da rede social atravÃ©s da aplicaÃ§Ã£o
	 * da mÃ©trica indegree de AnÃ¡lise de Redes Sociais e retorna um Map com o nome do estudante
	 * e o indegree.
	 */
	
	public Map<String,Integer> indegree(ArrayList<String> actors, MultiMap interactions){
		
		DirectedSparseMultigraph<String, String> graph = this.generateGhaph(actors, interactions);
		
		Collection<String> vertices = graph.getVertices();
		
		Map<String,Integer> indegreeValue = new HashMap<String, Integer>();
		
		int in;
		
		for (String student:vertices){
			in = graph.inDegree(student);
			indegreeValue.put(student, in);
		}
		
		// Save graph
		
		this.saveGraph(graph);
		
		return indegreeValue;
	}
	
	/*
	 * MÃ©todo que calcula o Ã­ndice outdegree de cada estudante da rede social atravÃ©s da aplicaÃ§Ã£o
	 * da mÃ©trica outdegree de AnÃ¡lise de Redes Sociais e retorna um Map com o nome do estudante
	 * e o outdegree.
	 */
	
	public Map<String,Integer> outdegree(ArrayList<String> actors, MultiMap interactions){
		
		DirectedSparseMultigraph<String, String> graph = this.generateGhaph(actors, interactions);
		
		Collection<String> vertices = graph.getVertices();
		
		Map<String,Integer> outdegreeValue = new HashMap<String, Integer>();
		
		int in;
		
		for (String student:vertices){
			in = graph.outDegree(student);
			outdegreeValue.put(student, in);
		}
		
		// Save graph
						
		this.saveGraph(graph);
		
		return outdegreeValue;
	}
	
	/*
	 * Gera um arquivo .graphml do grafo com o objetivo de ser plotado em ferramentas de visualizaÃ§Ã£o como Gephi e Pajek
	 */
	
	public void saveGraph(DirectedSparseMultigraph<String,String> graph){
		
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_hhmmss");
        Date date = new Date();
        String data = dateFormat.format(date);
		
		FileWriter file = null;
		try{
			file = new FileWriter(data + "graph.graphml");
			GraphMLWriter graphml = new GraphMLWriter();
			graphml.save(graph, file);
		}catch(Exception e) {System.out.println(e);};
	}
	
	/*
	 * MÃ©todo que nÃ£o estÃ¡ sendo utilizado pois o grafo jÃ¡ se comporta dessa maneira
	 */
	
	public MultiMap noDuplicatedInteractions(ArrayList<String> actors, MultiMap interactions){
	
		MultiMap clone = new MultiValueMap();
		ArrayList<String> edges = new ArrayList<String>();
		
		for (String j:actors){
			edges = (ArrayList<String>)interactions.get(j);
			if (edges != null){
				for (String i:edges){
					if (!(tuplaExist(i,(ArrayList<String>)clone.get(j)))){
						clone.put(j, i);
					}
				}
			}
			
		}
		
		System.out.println(clone);
		
		return clone;
		
	}
	
	/*
	 * MÃ©todo que compara se hÃ¡ tuplas duplicadas no MultiMap.
	 * MÃ©todo que nÃ£o estÃ¡ sendo utilizado pois o grafo jÃ¡ se comporta dessa maneira.
	 */
	
	public boolean tuplaExist(String x, ArrayList<String> list){
		
		boolean exist = false;
		
		if (list != null){
			for (String i:list){
				if (x.equals(i)){
					exist = true;
				}
			}
		}
		
		return exist;
		
	}


}
