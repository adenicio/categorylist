package com.adenix.categorylist.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassesGenerator implements GlobalVariaveis{

    public static void main(String[] args) throws IOException {


//        String current = new File( "." ).getCanonicalPath();
//        System.out.println("Current dir:"+current);
//
//        String directoryName = System.getProperty("user.dir");
//        System.out.println("Current Working Directory is = " +directoryName);
//
//        Path path = Paths.get("");
//        String directoryName2 = path.toAbsolutePath().normalize().toString();
//        System.out.println("Current Working Directory is = " +directoryName2);
//
////        System.out.printf("Informe o número para a tabuada:\n");
////        n = ler.nextInt();
        List domain = new ArrayList();
        domain.add("Categoria");
        domain.add("id");
        domain.add("chname");
//        domain.add("chdescricao");
//        domain.add("chcodigo");
//        domain.add("vlprecounit");
//        domain.add("ckdestaque");
//        domain.add("nrqtdedisponivel");
//        domain.add("nrqtdeminima");

//        ClassesGenerator.geraDomain(domain);
//        ClassesGenerator.geraApplication_yaml();

//        ClassesGenerator.geraMapper(domain);
//        ClassesGenerator.geraRepository(domain);
        ClassesGenerator.geraService(domain,"R");
//        ClassesGenerator.geraRequestsPost(domain);
//        ClassesGenerator.geraRequestsPut(domain);
//        ClassesGenerator.geraController(domain,"R");
//        System.out.printf("\nTabuada do %d foi gravada com sucesso em \"d:\\tabuada.txt\".\n", n);
    }

    private static void createPackages(String pathPackage) throws IOException{

        String fileName = path1+pathPackage;
        Path path = Paths.get(fileName);
        Files.createDirectories(path);
        System.out.println("New Directory created !  "+fileName);
    }

    private static void geraApplication_yaml() throws IOException {
        Scanner ler = new Scanner(System.in);
        int i, n;

        FileWriter arq = new FileWriter("C:\\Users\\079504631\\wsopenshift\\categorylist\\application.yml");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("spring:\n");
        gravarArq.printf("  datasource:\n");
        gravarArq.printf("    url: jdbc:mysql://localhost:3306/mysql?createDatabaseIfNotExist=true\n");
        gravarArq.printf("    username: root\n");
        gravarArq.printf("    password: root\n");
        gravarArq.printf("  jpa:\n");
        gravarArq.printf("    hibernate.ddl-auto: update\n");
        gravarArq.printf("    generate-ddl: true\n");
        gravarArq.printf("    properties.hibernate.dialect: org.hibernate.dialect.MySQL8Dialect\n");

        gravarArq.printf("\n");

        arq.close();

    }

    public static void geraMapper(List dominio) throws IOException {

        FileWriter arq = new FileWriter("C:\\sysadm\\rgv2022\\src\\main\\java\\com\\adenix\\rgv2022\\mapper\\" + dominio.get(0) + "Mapper.java");
        PrintWriter gravarArq = new PrintWriter(arq);
        String aliasClasse=String.valueOf(dominio.get(0)).toLowerCase();

        gravarArq.printf("import org.mapstruct.Mapper;\n");
        gravarArq.printf("import org.mapstruct.factory.Mappers;\n");

        gravarArq.printf("@Mapper(componentModel = \"spring\")\n");
        gravarArq.printf("public abstract class " + dominio.get(0) + "Mapper {\n");

        gravarArq.printf("public static final "+dominio.get(0) + "Mapper INSTANCE = Mappers.getMapper(" + dominio.get(0) + "Mapper.class);\n");

        gravarArq.printf("public abstract "+dominio.get(0) + " to" + dominio.get(0) + "(" + dominio.get(0) + "PostReqBody "+aliasClasse+"PostReqBody);\n");
        gravarArq.printf("public abstract "+dominio.get(0) + " to" + dominio.get(0) + "(" + dominio.get(0) + "PutReqBody "+aliasClasse+"PutReqBody);\n");

        gravarArq.printf("\n}");

        arq.close();
    }

    public static void geraController(List dominio, String screenLetter) throws IOException {
        String pathPackage = "controller";
        ClassesGenerator.createPackages(pathPackage);
        FileWriter arq = new FileWriter(path1+pathPackage+"\\" + dominio.get(0) + "Ctrl.java");
        PrintWriter gravarArq = new PrintWriter(arq);
        String aliasClasse=String.valueOf(dominio.get(0)).toLowerCase();

        gravarArq.printf("package "+grupo+"."+artifact+"."+pathPackage+";\n");
        gravarArq.printf("import "+grupo+"."+artifact+".domain."+dominio.get(0)+";\n");
        gravarArq.printf("import lombok.RequiredArgsConstructor;\n");
        gravarArq.printf("import lombok.extern.log4j.Log4j2;\n");
        gravarArq.printf("import org.springframework.beans.factory.annotation.Autowired;\n");
        gravarArq.printf("import org.springframework.http.HttpStatus;\n");
        gravarArq.printf("import org.springframework.http.ResponseEntity;\n");
        gravarArq.printf("import org.springframework.web.bind.annotation.*;\n");

        gravarArq.printf("import java.util.List;\n");

        gravarArq.printf("@RestController\n");
        gravarArq.printf("@RequestMapping(\""+aliasClasse+"\")\n");
        gravarArq.printf("@Log4j2\n");
        gravarArq.printf("@RequiredArgsConstructor\n");
        gravarArq.printf("public class " + dominio.get(0) + "Ctrl {\n");

        gravarArq.printf("@Autowired\n");
        gravarArq.printf("private final " + dominio.get(0) + "Serv "+aliasClasse+"Serv;\n");
        gravarArq.printf("\n");
        //metodo get
        if (screenLetter.equals("R")) {
            gravarArq.printf("@GetMapping\n");
            gravarArq.printf("public ResponseEntity<List<" + dominio.get(0) + ">> listAll(){\n");
            gravarArq.printf("return ResponseEntity.ok(" + aliasClasse + "Serv.listAll());\n");
            gravarArq.printf("}\n");
            gravarArq.printf("\n");
        }

        //metodo post
        if (screenLetter.equals("C")) {
            gravarArq.printf("@PostMapping\n");
            gravarArq.printf("@ResponseStatus\n");
            gravarArq.printf("public ResponseEntity<" + dominio.get(0) + "> save(@RequestBody " + dominio.get(0) + "PostReqBody " + aliasClasse + "PostReqBody){\n");
            gravarArq.printf("return new ResponseEntity<>(" + aliasClasse + "Serv.save(" + aliasClasse + "PostReqBody), HttpStatus.CREATED);\n");
            gravarArq.printf("}\n");
            gravarArq.printf("\n");
        }

        //metodo delete
        if (screenLetter.equals("D")) {
            gravarArq.printf("@DeleteMapping(path = \"/{id}\")\n");
            gravarArq.printf("public ResponseEntity<Void> delete(@PathVariable long id){\n");
            gravarArq.printf(aliasClasse + "Serv.delete(id);\n");
            gravarArq.printf("return new ResponseEntity<>(HttpStatus.NO_CONTENT);\n");
            gravarArq.printf("}\n");
            gravarArq.printf("\n");
        }

        //metodo put
        if (screenLetter.equals("U")){
            gravarArq.printf("@PutMapping\n");
            gravarArq.printf("public ResponseEntity<Void> replace(@RequestBody " + dominio.get(0) + "PutReqBody " + aliasClasse + "PutReqBody){\n");
            gravarArq.printf(aliasClasse + "Serv.replace(" + aliasClasse + "PutReqBody);\n");
            gravarArq.printf("return new ResponseEntity<>(HttpStatus.NO_CONTENT);\n");
            gravarArq.printf("}\n");
            gravarArq.printf("\n");
        }

        gravarArq.printf("}\n");
        arq.close();
    }

    public static void geraService(List dominio,String screenLetter) throws IOException {

        String pathPackage = "service";
        ClassesGenerator.createPackages(pathPackage);
        FileWriter arq = new FileWriter(path1+pathPackage+"\\" + dominio.get(0) + "Serv.java");
        PrintWriter gravarArq = new PrintWriter(arq);
        String aliasClasse=String.valueOf(dominio.get(0)).toLowerCase();

        gravarArq.printf("package "+grupo+"."+artifact+"."+pathPackage+";\n");
        gravarArq.printf("import "+grupo+"."+artifact+".domain."+dominio.get(0)+";\n");

        gravarArq.printf("import lombok.RequiredArgsConstructor;\n");
        gravarArq.printf("import org.springframework.http.HttpStatus;\n");
        gravarArq.printf("import org.springframework.stereotype.Service;\n");
        gravarArq.printf("import org.springframework.web.server.ResponseStatusException;\n");
        gravarArq.printf("import org.springframework.beans.factory.annotation.Autowired;\n");
        gravarArq.printf("import java.util.List;\n");

        gravarArq.printf("@Service\n");
        gravarArq.printf("@RequiredArgsConstructor\n");

        //Metodo buscar tudo
        if(screenLetter.equals("R")) {
            gravarArq.printf("public class " + dominio.get(0) + "Serv{\n");

            gravarArq.printf("@Autowired\n");
            gravarArq.printf("private final " + dominio.get(0) + "Repo " + aliasClasse + "Repo;\n");

            gravarArq.printf("public List<" + dominio.get(0) + "> listAll(){\n");

            gravarArq.printf("return " + aliasClasse + "Repo.findAll();\n");
            gravarArq.printf("\n}");
            gravarArq.printf("\n");


        //Metodo find by ID
            gravarArq.printf("public " + dominio.get(0) + " findById(long id){\n");
            gravarArq.printf("return  " + aliasClasse + "Repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,\"" + dominio.get(0) + " não encontrado\"));\n");
            gravarArq.printf("\n}");
            gravarArq.printf("\n");
        }

        //Metodo save
        if(screenLetter.equals("C")) {
            gravarArq.printf("public " + dominio.get(0) + " save(" + dominio.get(0) + "PostReqBody " + aliasClasse + "PostReqBody){\n");
            gravarArq.printf("return " + aliasClasse + "Repo.save(" + dominio.get(0) + "Mapper.INSTANCE.to" + dominio.get(0) + "(" + aliasClasse + "PostReqBody));\n");
            gravarArq.printf("\n}");
            gravarArq.printf("\n");
        }

        //Metodo delete
        if(screenLetter.equals("D")) {
            gravarArq.printf("public void delete(long id) {\n");
            gravarArq.printf(aliasClasse + "Repo.delete(findById(id));\n");
            gravarArq.printf("\n}");
            gravarArq.printf("\n");
        }

        //metodo replace
        if(screenLetter.equals("U")) {
            gravarArq.printf("public void replace(" + dominio.get(0) + "PutReqBody " + aliasClasse + "PutReqBody){\n");

            gravarArq.printf(dominio.get(0) + " " + "atual" + dominio.get(0) + " = findById(" + aliasClasse + "PutReqBody.getId());\n");
            gravarArq.printf(dominio.get(0) + " " + aliasClasse + " = " + dominio.get(0) + "Mapper.INSTANCE.to" + dominio.get(0) + "(" + aliasClasse + "PutReqBody);\n");
            gravarArq.printf(aliasClasse + ".setId(atual" + dominio.get(0) + ".getId());\n");
            gravarArq.printf(aliasClasse + "Repo.save(" + aliasClasse + ");\n");

            gravarArq.printf("\n}");
        }

        gravarArq.printf("\n}");

        arq.close();
    }

    public static void geraRepository(List dominio) throws IOException {
        String pathPackage = "repository";
        ClassesGenerator.createPackages(pathPackage);
        FileWriter arq = new FileWriter(path1+pathPackage+"\\" + dominio.get(0) + "Repo.java");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("package "+grupo+"."+artifact+"."+pathPackage+";\n");
        gravarArq.printf("import "+grupo+"."+artifact+".domain."+dominio.get(0)+";\n");
        gravarArq.printf("import org.springframework.data.jpa.repository.JpaRepository;\n");
        gravarArq.printf("\n");
        gravarArq.printf("public interface "+dominio.get(0)+"Repo extends JpaRepository<"+dominio.get(0)+",Long>{\n");
        gravarArq.printf("\n}");

        arq.close();
    }

    public static void geraRequestsPost(List dominio) throws IOException {

        Scanner ler = new Scanner(System.in);
        int i, n;

        FileWriter arq = new FileWriter("C:\\sysadm\\rgv2022\\src\\main\\java\\com\\adenix\\rgv2022\\requests\\"+dominio.get(0)+"PostReqBody.java");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("import lombok.Data;\n");

        gravarArq.printf("@Data\n");

        gravarArq.printf("public class "+dominio.get(0)+"PostReqBody{\n");

        for (i=2; i<dominio.size(); i++) {

            String campo = String.valueOf(dominio.get(i));

            String tipo = null;

            if(campo.substring(0,2).equals("ch")){
                tipo = "String";
            }else if(campo.substring(0,2).equals("vl")){
                tipo = "Double";
            }else if(campo.substring(0,2).equals("nr")){
                tipo = "Long";
            }else if(campo.substring(0,2).equals("ck")){
                tipo = "Boolean";
            }else if(campo.substring(0,2).equals("dt")){
                tipo = "Date";
            }else if(campo.substring(0,2).equals("id")) {
                tipo = "Long";
            }else{
                    tipo = "String";
            }

            gravarArq.printf("private "+tipo+" "+dominio.get(i)+";\n");
        }
        gravarArq.printf("\n}");

        arq.close();
    }

    public static void geraRequestsPut(List dominio) throws IOException {

        Scanner ler = new Scanner(System.in);
        int i, n;

        FileWriter arq = new FileWriter("C:\\sysadm\\rgv2022\\src\\main\\java\\com\\adenix\\rgv2022\\requests\\"+dominio.get(0)+"PutReqBody.java");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("import lombok.Data;\n");

        gravarArq.printf("@Data\n");

        gravarArq.printf("public class "+dominio.get(0)+"PutReqBody{\n");

        for (i=1; i<dominio.size(); i++) {

            String campo = String.valueOf(dominio.get(i));

            String tipo = null;

            if(campo.substring(0,2).equals("ch")){
                tipo = "String";
            }else if(campo.substring(0,2).equals("vl")){
                tipo = "Double";
            }else if(campo.substring(0,2).equals("nr")){
                tipo = "Long";
            }else if(campo.substring(0,2).equals("ck")){
                tipo = "Boolean";
            }else if(campo.substring(0,2).equals("dt")){
                tipo = "Date";
            }else if(campo.substring(0,2).equals("id")) {
                tipo = "Long";
            }else{
                    tipo = "String";
            }

            gravarArq.printf("private "+tipo+" "+dominio.get(i)+";\n");
        }
        gravarArq.printf("\n}");

        arq.close();
    }

    public static void geraDomain(List dominio) throws IOException {

        Scanner ler = new Scanner(System.in);
        int i, n;

        FileWriter arq = new FileWriter(path1+"domain\\"+dominio.get(0)+".java");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("import lombok.AllArgsConstructor;\n");
        gravarArq.printf("import lombok.Builder;\n");
        gravarArq.printf("import lombok.Data;\n");
        gravarArq.printf("import lombok.NoArgsConstructor;\n");

        gravarArq.printf("import javax.persistence.Entity;\n");
        gravarArq.printf("import javax.persistence.GeneratedValue;\n");
        gravarArq.printf("import javax.persistence.GenerationType;\n");
        gravarArq.printf("import javax.persistence.Id;\n");

        gravarArq.printf("@Data\n");
        gravarArq.printf("@AllArgsConstructor\n");
        gravarArq.printf("@NoArgsConstructor\n");
        gravarArq.printf("@Entity\n");
        gravarArq.printf("@Builder\n");
        gravarArq.printf("public class "+dominio.get(0)+"{\n");

        gravarArq.printf("@Id\n");
        gravarArq.printf("@GeneratedValue(strategy = GenerationType.IDENTITY)\n");

        for (i=1; i<dominio.size(); i++) {

            String campo = String.valueOf(dominio.get(i));

            String tipo = null;

            if(campo.substring(0,2).equals("ch")){
                tipo = "String";
            }else if(campo.substring(0,2).equals("vl")){
                tipo = "Double";
            }else if(campo.substring(0,2).equals("nr")){
                tipo = "Long";
            }else if(campo.substring(0,2).equals("ck")){
                tipo = "Boolean";
            }else if(campo.substring(0,2).equals("dt")){
                tipo = "Date";
            }else if(campo.substring(0,2).equals("id")){
                tipo = "Long";
            }else{
                tipo = "String";
            }

            gravarArq.printf("private "+tipo+" "+dominio.get(i)+";\n");
        }
        gravarArq.printf("\n}");

        arq.close();
    }
}
