package com.cms.gwt.fs.generator;

import java.io.PrintWriter;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;


/**
 * This class extends the GWT Generator. It is neither client nor server code.
 * 
 * At compile time, FactoryGenerator inspects the client classes which implement Instantiable 
 * and then generates a concrete implementation of the Factory interface.
 * 
 */
public class FactoryGenerator extends Generator {


	@Override
	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {

		logger.log(TreeLogger.INFO, "Generating source for " + typeName, null);

		TypeOracle typeOracle = context.getTypeOracle();

		// Type name is the empty factory class - i.e. ReflectiveFactory. 
		JClassType clazz = typeOracle.findType(typeName);
		
		if (clazz == null) {
			
			// Unable to find the type passed from the GWT.create(String).
			
			logger.log(TreeLogger.ERROR, "Unable to find metadata for type '" + typeName + "'", null);

			throw new UnableToCompleteException();

		}
		
		try {

			logger.log(TreeLogger.INFO, "Generating source for " + clazz.getQualifiedSourceName(), null);
		 
			JClassType reflectableType = typeOracle.getType("com.cms.gwt.fs.client.reflection.Instantiable");
		
			// Get sourceWriter for the wrapper class - i.e. RelativeFactoryWrapper.
			SourceWriter sourceWriter = getSourceWriter(clazz, context, logger);
		
			if (sourceWriter != null) {
		
				sourceWriter.println("public " + reflectableType.getQualifiedSourceName()
						+ " newInstance(String className) {");

				// All get types? This is probably inefficient. 
				// Fix it later. Maybe get all types in a specific package?
				JClassType[] types = typeOracle.getTypes();
		
				int count = 0;

				// For now, it's still using for loop and if statement to iterate through all the filenames.
				// Change this to a static hashmap?
				for (int i = 0; i < types.length; i++) {

					// if the type is not an interface and implements Instantiable.
					if (types[i].isInterface() == null
							&& types[i].isAssignableTo(reflectableType)) {

						if (count == 0) {

							sourceWriter.println("   if(\""
									+ types[i].getQualifiedSourceName()
									+ "\".equals(className)) {"
									+ " return new "
									+ types[i].getQualifiedSourceName() + "();"
									+ "}");
						} else {
		
							sourceWriter.println("   else if(\""
									+ types[i].getQualifiedSourceName()
									+ "\".equals(className)) {"
									+ " return new "
									+ types[i].getQualifiedSourceName() + "();"
									+ "}");
						}
						
						count++;
					}
				}
		
				sourceWriter.println("return null;");

				sourceWriter.println("}");

				sourceWriter.commit(logger);

				logger.log(TreeLogger.INFO, "Done Generating source for "
						+ clazz.getName(), null);
				
				return clazz.getQualifiedSourceName() + "Wrapper";
			}
		} catch (NotFoundException e) {

			e.printStackTrace();
		}

		//return null;
		return clazz.getQualifiedSourceName() + "Wrapper";
	}		

			 
	/**
	 * Returns a source writer for the factory wrapper class. i.e. ReflectiveFactoryWrapper.
	 * 
	 * @param classType
	 * @param context
	 * @param logger
	 * @return
	 */
	public SourceWriter getSourceWriter(JClassType classType, GeneratorContext context, TreeLogger logger) 
	{

		String packageName = classType.getPackage().getName();
	
		String simpleName = classType.getSimpleSourceName() + "Wrapper";

		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
			
		composer.addImplementedInterface("com.cms.gwt.fs.client.reflection.Factory");
		
		PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);

		if (printWriter == null) {
			
			return null;
			
		} else {
			
			SourceWriter sw = composer.createSourceWriter(context, printWriter);

			return sw;					
			
		}
	}	  
}
