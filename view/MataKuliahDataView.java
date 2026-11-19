package view;

import model.MataKuliah;

public class MataKuliahDataView extends DataView<MataKuliah> {

    @Override
    protected String getPrimaryKeyValue(MataKuliah data) {
        return data.getKode();
    }

    @Override
    protected String[] getFields() {
        return new String[]{"kode", "nama", "jumlahSks", "semester"};
    }

    @Override
    protected MataKuliah convertValuesToModel(String[] values) {
        int jumlahSks = Integer.parseInt(values[2].trim());
        int semester = Integer.parseInt(values[3].trim());
        return new MataKuliah(values[0].trim(), values[1].trim(), jumlahSks, semester);
    }

    @Override
    protected String[] convertModelToValues(MataKuliah data) {
        return new String[]{
            data.getKode(),
            data.getNama(),
            String.valueOf(data.getJumlahSks()),
            String.valueOf(data.getSemester())
        };
    }
}