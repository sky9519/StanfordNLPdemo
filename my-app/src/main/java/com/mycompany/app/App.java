package com.mycompany.app;

import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordNamedEntityRecognizer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordParser;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;
import java.io.BufferedReader;  
import java.io.FileReader; 

import java.io.IOException;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

public class App
{

    public static void main(String[] args)
            throws UIMAException, IOException
    {
        CollectionReader reader = createReader(
                TextReader.class,
                TextReader.PARAM_SOURCE_LOCATION, "src/main/resources",
                TextReader.PARAM_LANGUAGE, "en",
                TextReader.PARAM_PATTERNS, new String[] { "[+]*.txt" });

        AnalysisEngineDescription seg = createEngineDescription(StanfordSegmenter.class);

        AnalysisEngineDescription ner = createEngineDescription(
                StanfordNamedEntityRecognizer.class);

        AnalysisEngineDescription parser = createEngineDescription(StanfordParser.class);

        AnalysisEngineDescription writer = createEngineDescription(StanfordNLP.class);

        SimplePipeline.runPipeline(reader, seg, ner, parser, writer);
        
        String line;  
        int count = 0;  
        int lineCount = 0;
  
        //Opens a file in read mode  
        FileReader file = new FileReader("src/main/resources/document.txt");  
        BufferedReader br = new BufferedReader(file);  
  
        //Gets each line till end of file is reached  
        while((line = br.readLine()) != null) {  
        	lineCount++;
            //Splits each line into words  
            String chars[] = line.split("");  
            //Counts each word  
            count = count + chars.length;  
  
        }  
       
        System.out.println("Number of Lines present in given file: " + lineCount);  
        System.out.println("Number of Characters present in given file: " + count);  
        
        br.close(); 
        
    } /* main() */
} /* class */

