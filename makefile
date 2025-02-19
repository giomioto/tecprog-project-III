# Makefile para compilar e rodar o programa Java

# Nome das classes principais
MAIN_CLASS = Main

# Caminho do compilador Java
JAVAC = javac
JAVA = java

# Diretório onde os arquivos .class serão gerados
CLASS_DIR = bin

# Target para compilar o programa
all: compile run

# Cria o diretório bin se ele não existir
$(CLASS_DIR):
	mkdir -p $(CLASS_DIR)

# Compila o código Java
compile: $(CLASS_DIR)
	$(JAVAC) -d $(CLASS_DIR) *.java

# Executa o programa Java
run:
	$(JAVA) -cp $(CLASS_DIR) $(MAIN_CLASS)

# Limpa os arquivos .class
clean:
	rm -f $(CLASS_DIR)/*.class
