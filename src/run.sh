#!/bin/bash
mvn install
mvn exec:java -pl swing-dynamic-gui-examples -Dexec.mainClass="pl.edu.wat.wcy.swingdynamicgui.examples.crud.CrudAppExample"

