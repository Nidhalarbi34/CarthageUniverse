package tn.enicarthage.EnicarthageUniverse.Requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequest {
    private  Long idEtud;
    private String matiere;
    private Float value;

}
