package mg.tife.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class DataLoader {

    @Value("classpath:/bible.fr.json")
    private Resource bibleJSON;
    
    private final ObjectMapper objectMapper;
    
    VectorStore vectorStore;

	public DataLoader(VectorStore vectorStore,ObjectMapper objMap) {
		super();
		this.vectorStore = vectorStore;
		this.objectMapper = objMap;
	}
	
	
	//@Bean
    public ApplicationRunner initializer() {
        return args -> {
        	System.out.println("initialiser**************");
            if (vectorStore.similaritySearch("qui est abraham").isEmpty()) {
            	initStore();
           }
        };
    }
    
    public void initStore() throws IOException {
    	System.out.println("============== loading ....");
    	InputStream inputStream = bibleJSON.getInputStream();
        List<Map<String, Object>> bibleBooks = objectMapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {});
        
        int chap = 1;
        int vrs = 1;
        List<Document> versesDoc = new ArrayList<Document>();
        System.out.println("============== reading ....");
        for (Map<String, Object> book : bibleBooks) {
            List<List<String>> chapters = (List<List<String>>) book.get("chapters");
            chap = 1;
            for (List<String> verses : chapters) {
            	vrs = 1;
            	for(String verse : verses) {
            		versesDoc.add(new Document(verse,Map.of("abbrev",book.get("abbrev"),"name",book.get("name"),"chapiter",chap++,"verse",vrs++)));
            	}
            }
        }
        
        int batchSize = 1000;
        int totalSize = versesDoc.size();
        int latestSuccess = 30000;
        

        for (int i = latestSuccess; i < totalSize; i += batchSize) {
            int end = Math.min(i + batchSize, totalSize);
            List<Document> subList = versesDoc.subList(i, end);
            System.out.println( i + " - "+ end + " adding");
            vectorStore.add(subList);
            System.out.println( i + " - "+ end + " / "+totalSize+ "  ADDED");
        }
        
        System.out.println("Completed ");
        
        
    }
}