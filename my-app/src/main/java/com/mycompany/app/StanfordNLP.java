package com.mycompany.app;



import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.NP;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

public class StanfordNLP
        extends JCasConsumer_ImplBase
{

    @Override
    public void process(JCas aJCas)
            throws AnalysisEngineProcessException
    {
    	
    	int count = 0;

    
        for (Sentence sentence : JCasUtil.select(aJCas, Sentence.class)) {
 
    
            for (NP nounphrase : JCasUtil.selectCovered(aJCas, NP.class, sentence)) {
 
        
                for (NamedEntity ne : JCasUtil
                        .selectCovered(aJCas, NamedEntity.class, nounphrase)) {
                	count++;
                    System.out.println(
                            // "NP " + nounphrase.getCoveredText() + 
                    		"\tNN : " + ne.getCoveredText());
                    		

                } 
            } 
        } 
    
        System.out.println("Number of Nouns present in given file: " + count);  
    } 
}