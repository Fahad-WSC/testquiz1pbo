import model.Mahasiswa;
//tahap 3
public class MahasiswaDataView extends DataView<Mahasiswa> {

    @Override
    protected String getPrimaryKeyValue(Mahasiswa data) {
        return data.getNim();
    }

    @Override
    protected String[] getFields() {
        return new String[]{"nim", "nama", "email", "umur"};
    }

    @Override
    protected Mahasiswa convertValuesToModel(String[] values) {
        int umur = Integer.parseInt(values[3].trim());
        return new Mahasiswa(values[0].trim(), values[1].trim(), values[2].trim(), umur);
    }

    @Override
    protected String[] convertModelToValues(Mahasiswa data) {
        return new String[]{
            data.getNim(),
            data.getNama(),
            data.getEmail(),
            String.valueOf(data.getUmur())
        };
    }
}