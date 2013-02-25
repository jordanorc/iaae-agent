package iaae.ontology;

import iaae.models.Thesis;
import iaae.models.Tuple;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.shared.DoesNotExistException;
import com.hp.hpl.jena.shared.PropertyNotFoundException;
import com.hp.hpl.jena.vocabulary.RDFS;

public class ThesesGenerator {

	private OntModel ontModel;

	/**
	 * Leitura do ficheiro OWL e criação do modelo com raciocinador
	 */
	public ThesesGenerator(InputStream ontology) {
		// criando modelo utilizando reasoner
		ontModel = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);

		// lendo o arquivo RDF/XML
		try {
			ontModel.read(ontology, "RDF/XML");
		} catch (DoesNotExistException e) {
			System.out
					.println("Ocorreu o seguinte problema durante a leitura do ficheiro RDF/XML: "
							+ e.getMessage());
		}
	}

	public List<Individual> getIndividuals() {
		List<Individual> individuals = new ArrayList<Individual>();

		for (Iterator i = ontModel.listIndividuals(); i.hasNext();) {
			individuals.add(((Individual) i.next()));
		}

		return individuals;
	}

	public Individual getIndividual(String name) {
		Individual individual = null;
		List<Individual> individuals = this.getIndividuals();

		for (Individual i : individuals) {
			if (i.getLocalName() == name) {
				individual = i;
			}
		}

		return individual;
	}

	public List<Individual> getIndividuals(Property property) {
		List<Individual> individuals = this.getIndividuals();
		List<Individual> result = new ArrayList<Individual>();

		for (Individual i : individuals) {
			result.add(i);
		}

		return result;
	}

	public List<Property> getProperties() {
		List<Property> properties = new ArrayList<Property>();
		for (Iterator i = ontModel.listAllOntProperties(); i.hasNext();) {
			OntProperty prop = (OntProperty) i.next();
			if (prop.getProperty(RDFS.comment) != null) {
				if (this.getIndividuals(prop).size() > 0) {
					properties.add(prop);
				}
			}
		}
		return properties;
	}

	public Tuple<Individual, Property> getRandomIndividual(
			List<Individual> individuals, List<Property> properties) {
		Tuple<Individual, Property> result = null;

		Individual individual = null;
		Property property = null;
		Random randomGenerator = new Random();

		while (result == null) {
			individual = individuals.get(randomGenerator.nextInt(individuals
					.size()));
			property = properties
					.get(randomGenerator.nextInt(properties.size()));

			if (individual.getProperty(property) != null) {
				result = new Tuple<Individual, Property>(individual, property);
			}

		}

		return result;

	}

	public Statement getObject(List<Individual> individuals, Property property,
			Node exclude) {
		List<Individual> ind = new ArrayList<Individual>(individuals);
		Collections.shuffle(ind);
		for (Individual individual : ind) {
			if ((individual.getProperty(property) != null)
					&& (!individual.getProperty(property).asTriple()
							.getObject().equals(exclude))) {
				return individual.getProperty(property);
			}
		}
		return null;
	}

	public List<Thesis> getTheses(String theme, Integer limit) {
		List<Thesis> theses = new ArrayList<Thesis>();

		List<Individual> individuals = this.getIndividuals();
		List<Property> properties = this.getProperties();
		Random randomGenerator = new Random();

		// System.out.println(this.checkSimilarity(individuals.get(4),
		// individuals.get(7), properties.get(1)));

		while (theses.size() < limit) {
			// busca tese
			Tuple<Individual, Property> individual = this.getRandomIndividual(
					individuals, properties);

			// randomicamente determina se a tese será válida ou inválida
			boolean valid = randomGenerator.nextBoolean();

			// se não for válido, gera outro valor para invalidá-la
			Statement statement = null;
			if (!valid) {
				statement = this.getObject(individuals, individual.getF2(),
						individual.getF1().getProperty(individual.getF2())
								.asTriple().getObject());
			} else {
				statement = individual.getF1().getProperty(individual.getF2());
			}

			if (statement != null) {

				// inicia a geração da tese
				String thesis = individual.getF2().getProperty(RDFS.comment)
						.getString();
				thesis = thesis.replace("{{ SUJ }}", individual.getF1()
						.getLocalName());

				try {
					thesis = thesis.replace("{{ ADJ }}",
							statement.getProperty(RDFS.label).getString());
				} catch (PropertyNotFoundException | NullPointerException e) {
					thesis = thesis.replace("{{ ADJ }}", statement.asTriple()
							.getObject().getLocalName());
				}

				thesis = thesis.replace("{{ ART }} ", "");

				if (!theses.contains(individual)) {
					theses.add(new Thesis(thesis, valid));
				}
			}
		}
		return theses;

	}

}
