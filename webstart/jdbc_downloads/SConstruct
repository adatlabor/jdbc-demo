# Compile .java files, generate .class files
javac_env = Environment(JAVACFLAGS='-encoding UTF-8', JAVACLASSPATH=['.', 'lib/ojdbc7.jar'])
my_class_files=javac_env.Java('classes', 'src')

# Generate an unsigned .jar file from .class files
jar_env = Environment(JARCHDIR='classes')
jar_env.Jar(target='unsigned.jar', source=['classes', 'conf/MANIFEST.MF'] )

# Sign .jar file
# Create builder (jarsigner)
jarsigner_build = Builder(action='jarsigner -keystore $KEYSTORE '
    '-storepass webstart -tsa $TSA -signedjar $TARGET $SOURCE webstart')
# Set parameters
jarsigner_env = Environment(BUILDERS={'JarSigner': jarsigner_build}, KEYSTORE='conf/webstart.keystore', TSA='http://timestamp.digicert.com/')
# Sign .jar file
jar_file=jarsigner_env.JarSigner(target='MyJwsApplication.jar', source='unsigned.jar')

# Deploy .jar file and JDBC driver into 'web' directory
jardeploy_env = Environment()
jardeploy_env.Install(target='web/', source=jar_file+['lib/ojdbc7.jar'])
