package ge.exen.services;

import ge.exen.DAO.ExamDao;
import ge.exen.dto.ExamDTO;
import ge.exen.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.Map;

@Service
public class ExamService implements IExamService {

    @Autowired
    ExamDao exdao;
    @Autowired
    IExamMaterial materials;


    public long process(ExamDTO values) {
        String polishedDate = values.getStartDate().replace('T', ' ');
        values.setStartDate(polishedDate);

        int durationInMinutes = values.getMinutes() + 60 * values.getHours();

        Exam newex = new Exam(values.getFullName(),
                values.getStartDate(),
                durationInMinutes,
                values.getVariants());


        long val = exdao.create(newex);
        if (val < 0) return val;




        return newex.getID();
    }


    public int setFiles(Map<String, MultipartFile> input, Long id){
        HashMap<Integer, MultipartFile> files = new HashMap<>();
        for(Map.Entry<String,MultipartFile> ent : input.entrySet()){

            if(!ent.getValue().isEmpty()){System.out.println(ent.getKey());
                files.put(Integer.parseInt(ent.getKey().substring(10)), ent.getValue());
            }
        }

        return materials.storeFiles(files, id);
    }
}
