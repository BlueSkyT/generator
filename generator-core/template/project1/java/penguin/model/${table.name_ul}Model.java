package penguin.model;

public class ${table.name_ul}Model {

    <#list table.columns as column>
    private ${column.codeType!} ${column.name_ulfl!};
    </#list>

    <#list table.columns as column>
    public ${column.codeType!} get${column.name_ul}(){
        return this.${column.name_ulfl!};
    }
    public void set${column.name_ul}(${column.codeType!} ${column.name_ulfl!}){
        this.${column.name_ulfl!} = ${column.name_ulfl!};
    }
    </#list>
}