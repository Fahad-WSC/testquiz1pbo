package view;

import model.Ruangan;

public class RuanganDataView extends DataView<Ruangan> {

    @Override
    protected String getPrimaryKeyValue(Ruangan data) {
        return data.getId();
    }

    @Override
    protected String[] getFields() {
        return new String[]{"id", "nama", "lokasiLantai", "kapasitas"};
    }

    @Override
    protected Ruangan convertValuesToModel(String[] values) {
        int lokasiLantai = Integer.parseInt(values[2].trim());
        int kapasitas = Integer.parseInt(values[3].trim());
        return new Ruangan(values[0].trim(), values[1].trim(), lokasiLantai, kapasitas);
    }

    @Override
    protected String[] convertModelToValues(Ruangan data) {
        return new String[]{
            data.getId(),
            data.getNama(),
            String.valueOf(data.getLokasiLantai()),
            String.valueOf(data.getKapasitas())
        };
    }
}