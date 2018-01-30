package com.nuoyuan.compiler;

import com.google.auto.service.AutoService;
import com.nuoyuan.annotation.Classname;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import static javax.lang.model.element.Modifier.PUBLIC;


@AutoService(Processor.class)
public class ClassNameProcessor extends AbstractProcessor {
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Classname.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        ClassName hoverboard = ClassName.get("com.mattel", "Hoverboard");
        ClassName list = ClassName.get("java.util", "List");
        ClassName arrayList = ClassName.get("java.util", "ArrayList");


        TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);

        MethodSpec beyond = MethodSpec.methodBuilder("beyond")
                .returns(listOfHoverboards)
                .addStatement("$T result = new $T<>()", listOfHoverboards, arrayList)
                .addStatement("result.add(new $T())", hoverboard)
                .addStatement("result.add(new $T())", hoverboard)
                .addStatement("result.add(new $T())", hoverboard)
                .addStatement("return result")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(PUBLIC, Modifier.FINAL)
                .addMethod(beyond)
                .build();
        JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
                .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}















