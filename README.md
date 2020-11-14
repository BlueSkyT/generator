# generator
一次配置，远离增删改查。
从前端到后台的代码生成工具

#### 开发环境
- java jdk1.8
- maven 3.2+
- mysql(可选)

#### 打包
- 本项目打包
    `mvn package`
- 拷贝依赖 
  `mvn dependency:copy-dependencies -DoutputDirectory=lib`
  
### 示例
- json + freemarker
```
Renderer renderer = new FreeMarkerRenderer(); //freemarker渲染器
String templatePath = "template path";//模板路径
String sourcePath = "source path";//生成的源码目录
MetaResolve resolve = new JsonFileResolve("gen.txt");
CRUDGenerator generator = new CRUDGenerator(templatePath, sourcePath, resolve, renderer);
generator.generate();
```
- jdbc + freemarker
```
String url = "jdbc:mysql://xxxx:3306/xxxx?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
String username = "username";
String password = "password";
MetaResolve resolve = new JdbcResolve(url, username, password, "com.mysql.cj.jdbc.Driver");

CRUDGenerator generator = new CRUDGenerator(templatePath, sourcePath, resolve, renderer);
generator.generate();
```
- jdbc + freemarker
```
MetaResolve resolve = new MetaResolve() {
    @Override
    public MetaContext resolveMeta() {
        MetaContext context = new MetaContext();
        context.setTables(new ArrayList<>());
        context.setNamingRules(Constants.RULES_LOWERCASE);
        MetaTable table = new MetaTable();
        table.setName("user");
        context.getTables().add(table);

        table.setColumns(new ArrayList<>());
        MetaColumn column = new MetaColumn();
        column.setName("username");
        column.setType("varchar");
        column.setLength(100);
        table.getColumns().add(column);

        MetaColumn column1 = new MetaColumn();
        column1.setName("password");
        column1.setType("varchar");
        column1.setLength(100);
        table.getColumns().add(column1);
        return context;
    }

    @Override
    public LinkedHashMap<String, String> resolveTypeMap() {
        LinkedHashMap<String, String> dbTypeMap = new LinkedHashMap<>();
        dbTypeMap.put("varchar", "String");
        dbTypeMap.put("int", "Integer");
        dbTypeMap.put("tinyint", "Boolean");
        return dbTypeMap;
    }
};

CRUDGenerator generator = new CRUDGenerator(templatePath, sourcePath, resolve, renderer);
generator.generate();
```