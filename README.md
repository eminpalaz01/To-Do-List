# To-Do List Projesi

Bu proje, kullanıcıların yapılacaklar listesi oluşturmasına ve yönetmesine olanak tanır. Authorizitaion sistemi vardır ve her token 30 dakika geçerlidir.
Todo lar için Dosyaları upload etme ve bir resim yükleme şuanda aktif deildir.

Backend  -> Java(Spring boot, Spring Security, Hibernate)  Database olarak H2 kullandım gömülü backend'de

Frontend -> Javascript(React.js, Material UI)

## Başlangıç

Bu adımlar, projeyi yerel makinenizde çalıştırmak için gereken temel adımları içerir.

### Gereksinimler

Bu projeyi çalıştırmak için aşağıdaki yazılımların yüklü olması gerekmektedir:

- Java Development Kit (JDK) 11: [Java 11 indirme sayfası](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- Node.js ve npm: [Node.js indirme sayfası](https://nodejs.org/)
- Git: [Git indirme sayfası](https://git-scm.com/downloads)
- Maven: [Maven indirme sayfası](https://maven.apache.org/download.cgi)

### Kurulum

1. Bu depoyu klonlayın:

git clone 

https://github.com/eminpalaz01/To-Do-List.git

2. Frontend ve backend klasörlerine gidin:

cd ToDoListProjesi/frontend

cd ToDoListProjesi/springbootcase

3. Frontend klasörüne aşağıdaki komutu çalıştırarak bağımlılıkları yükleyin:

npm install

### Çalıştırma

1. Backend uygulamasını çalıştırmak için backend klasöründe aşağıdaki komutu kullanın:
 
./mvnw spring-boot:run

2. Frontend uygulamasını çalıştırmak için frontend klasöründe aşağıdaki komutu kullanın:
 
npm start

3. Tarayıcınızı açın ve `http://localhost:3000` adresine giderek uygulamayı görüntüleyin.
 
not: Backend'e  'http://localhost:8080/swagger-ui/index.html#/' bağlantısından uygulama çalışır haldeyken ulaşıp API dökümantasyonuna bakabilirsiniz.

not: Database'e 'http://localhost:8080/h2-console' bağlantısından uygulama çalışır haldeyken

username : sa

password : 123

bilgileri ile bağlanabilirsiniz.
