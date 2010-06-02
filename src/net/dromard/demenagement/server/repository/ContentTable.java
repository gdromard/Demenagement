package net.dromard.demenagement.server.repository;

import java.io.File;

import net.dromard.demenagement.shared.model.Content;

public class ContentTable extends AbstractTable<Content> {

    @Override
    protected String getComment() {
        return "Content Table";
    }

    @Override
    protected String[] getHeader() {
        return new String[] { "ID", "NAME", "QUANTITY", "DESCRIPTION", "CARTON_ID" };
    }

    @Override
    protected String[] toArray(Content model) {
        if (model != null) {
            return new String[] { "" + model.getId(), model.getName(), model.getQuantity() + "", model.getDescription(), model.getCartonId() + "" };
        }
        return null;
    }

    @Override
    protected Content toModel(String[] values) {
        Content model = new Content();
        if (values.length >= 3) {
            model.setId(Integer.parseInt(values[0]));
            model.setName(values[1]);
            model.setQuantity(Integer.parseInt(values[2]));
            model.setDescription(values[3]);
            model.setCartonId(Integer.parseInt(values[4]));
            return model;
        }
        return null;
    }

    @Override
    protected File getFile() {
        return new File("ContentTable.csv");
    }
}
