package iaae.ontology;

public class Main {

	/**
	 * Args [0] - Nome da ontologia, ou seja, o dominio. Args [1] - Assunto para
	 * realizar a consulta Args [2] - Parametro para consulta especifica Args
	 * [3] - Diretório atual da Ontologia
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * @param [0] - Ornitologia ou Desenhos
		 * @param [1] - Caso [0] seja Ornitologia, [1] podera ser: Ordem -
		 *        retorna todos os passaros e as respesctivas ordens
		 *        Caracteristicas - retorna os passaros com sus respectivas
		 *        caracteristicas // * "Ordem Especifica" - esta oção é
		 *        combinada com o parametro [2]. * @param [2] - é colocado o
		 *        nome da ave e retornado a ave e sua respcitiva ordem (correção
		 *        da tese)
		 * 
		 * @param [1] - Caso [0] seja Desenhos, [1] podera ser:
		 *        "Classificacao Personagem" - retorna o nome do personagem e se
		 *        ele é um personagem classificado como principal ou secundario.
		 *        "Especifico" - retorna a classificação especifica de um
		 *        personagem (personagem classificado como secundario ou
		 *        primário).
		 * @param [2] - Combinado com o parametro [1] (Desenhos). No parametro
		 *        [2] é passado o nome do personagem
		 * 
		 * 
		 * @param [3] - é passado o diretório onde se encontra a Ontologia.
		 */
		ThesesGenerator generator = null;

		System.out.println("Consulta na Ontologia do dominio Ornitologia\n");

		/*
		 * generator = new ThesesGenerator("Ornitologia",
		 * "file:////home/celestrini/Desktop/desenvolvimento/iaae/iaee-agent/");
		 */
		// System.out.println(consulta.getTese());

		/* generator.getTheses("Ave", 10); */

		/*
		 * consulta = null;
		 * 
		 * System.out.println("Consulta na Ontologia do dominio Desenhos\n");
		 * consulta = new Consulta("Desenhos", "Classificacao Especifico",
		 * "Piupiu",
		 * "file:////home/celestrini/Desktop/desenvolvimento/iaae/iaee-agent/");
		 * System.out.println(consulta.getTese());
		 * 
		 * consulta = null;
		 */
	}

}
