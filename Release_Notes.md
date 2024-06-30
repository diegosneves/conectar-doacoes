# Release Notes



---
## **_Release 1.0.0_**

**Commit** 83c5874bc129c9e159ec8920d59a049443cadc89:

Esse commit adiciona configurações de OpenAPI, segurança web e CORS. Três classes de configuração foram criadas:

**Arquivos Adicionados:** `CorsConfig.java`, `OpenApiConfig.java`, `WebSecurityConfig.java`

**Alterações:**

- **CorsConfig.java**: Essa classe de configuração foi criada para habilitar o compartilhamento de recursos de origem cruzada (CORS). Ela fornece um bean para `CorsFilter`, que é utilizado para tratar requisições CORS, permitindo requisições de qualquer origem, quaisquer cabeçalhos e qualquer método.

- **OpenApiConfig.java**: Essa classe de configuração foi criada para aprimorar a documentação da API. Configura as informações detalhadas e as tags que são exibidas na documentação do Swagger/OpenAPI.

- **WebSecurityConfig.java**: Essa classe de configuração foi criada para definir as configurações de segurança web da aplicação.

**Nota:** A principal ênfase deste commit é melhorar a documentação da API, segurança web e compartilhamento de recursos de origem cruzada, fornecendo classes de configuração para cada um deles.

---

**Commit** 792c0a9834c279579c9f88b6a84cec59df631b07:

Este commit reorganiza os pacotes e remove um teste não utilizado. Os principais arquivos do pacote "diegosneves.github.conectardoacoes" foram movidos para pacotes mais específicos sob "diegosneves.github.conectardoacoes.adapters.rest". Além disso, o arquivo de teste "ConectarDoacoesApplicationTests.java" foi removido por não estar sendo utilizado.

**Arquivos Alterados:** `ConectarDoacoesApplication.java`, `CorsConfig.java`, `OpenApiConfig.java`, `WebSecurityConfig.java`

**Alterações:**

- Os pacotes dos arquivos foram alterados para "diegosneves.github.conectardoacoes.adapters.rest" para melhorar a organização do projeto.
- O teste "ConectarDoacoesApplicationTests.java" foi removido do repositório.

**Nota:** A principal ênfase deste commit é melhorar a organização dos pacotes do projeto e remover os testes que não estão sendo utilizados.

---

**Commit** bf127e04eb7ef579e12fd578ddeac74ce944ecd9:

Este commit adiciona manipulação de UUID e exceções relacionadas. Uma nova classe de utilidade para lidar com operações relacionadas ao UUID é introduzida. Além disso, ele adiciona um novo tipo de exceção (UuidUtilsException) para casos em que o UUID fornecido é inválido. O commit também inclui um arquivo de enumerador (ExceptionDetails) para fornecer detalhes mais claros sobre vários cenários de falha.

**Arquivos Adicionados:** `ExceptionDetails.java`, `UuidUtilsException.java`, `UuidUtils.java`

**Alterações:**

- **ExceptionDetails.java**: Enumera detalhes de exceções para mensagens de erro mais claras. Definido o erro INVALID_UUID_FORMAT_MESSAGE.

- **UuidUtilsException.java**: Uma nova exceção personalizada para lidar com cenários inválidos do UUID.

- **UuidUtils.java**: Classe utilitária para realizar operações relacionadas ao UUID. Funções como `generateUuid()` e `isValidUUID()` são fornecidas.

**Nota:** A ênfase principal deste commit é melhorar a manipulação e validação de UUID, adicionando novas classes e exceções para lidar com cenários relacionados ao UUID.

---

**Commit** 9451eacb312d1e03f38b75063c3a491df674a447:

Este commit adiciona uma nova classe de usuário (User) e seu contrato (UserContract). Também inclui uma classe de exceções (UserCreationFailureException) para casos de falha na criação do usuário. Um arquivo de teste para a classe User também foi adicionado para garantir adequada cobertura de testes.

**Arquivos Adicionados:** `User.java`, `UserContract.java`, `UserProfile.java`, `UserCreationFailureException.java`, `UserTest.java`

**Alterações:**

- `User.java`: Define a classe User e os métodos para validação de dados do usuário durante a criação do mesmo.

- `UserContract.java`: Define o contrato para a classe User.

- `UserProfile.java`: Define o perfil do usuário, podendo ser DOADOR ou BENEFICIARIO.

- `UserCreationFailureException.java`: Define uma nova exceção customizada, lançada quando ocorre uma falha na criação de um usuário.

- `UserTest.java`: Adiciona testes para a classe User, garantindo que o usuário seja corretamente criado e validado.

**Nota**: O principal objetivo deste commit é a adição da classe User, com todas as suas especificidades e testes necessários.

---

**Commit** 374f567670c9d8271a747027111fc84e65e5e72f:

Este commit adiciona documentação detalhada para várias classes e métodos, com o objetivo de melhor explicar o propósito e o uso dessas classes e métodos. Também altera a exceção apresentada na classe 'UserCreationFailureException' e remove a dependência do PostgreSQL do `pom.xml`, optando pelo uso do H2 como banco de dados.

**Arquivos Alterados:** `pom.xml`, `User.java`, `UserContract.java`, `ExceptionDetails.java`, `UserCreationFailureException.java`.

**Alterações:**

- Removida a dependência do PostgreSQL no `pom.xml`

- `User.java`: Adicionada documentação detalhada para a classe e seus métodos.

- `UserContract.java`: Adicionada documentação detalhada, explicando cada método da interface e seus respectivos propósitos.

- `ExceptionDetails.java`: Alterada a exceção USER_NAME_INVALID para USER_CREATION_ERROR e adicionada nova exceção ADDRESS_CREATION_ERROR.

- `UserCreationFailureException.java`: Adicionada documentação detalhada explicando a classe de exceção e seus possíveis usos.

**Nota**: A ênfase deste commit está na melhor documentação das classes afetadas e na mudança para o uso do H2 como banco de dados em vez do PostgreSQL.

---

**Commit** fab77ed4449460b8d8381357a052daa49a06240c:

Este commit cria classes de exceção personalizadas para tratar erros específicos durante a criação de entidades, como `Address`, `Donation` e `Shelter`. Além disso, expande o enum `ExceptionDetails` para incluir detalhes sobre os erros de criação de `Donation` e `Shelter`. As novas classes de exceção estendem de `RuntimeException` e podem ser usadas para lançar erros personalizados com mensagens claras e específicas.

**Arquivos Alterados:** `ExceptionDetails.java`, `AddressCreationFailureException.java`, `DonationRegisterFailureException.java`, `ShelterCreationFailureException.java`, `UuidUtilsException.java`.

**Alterações:**

- `ExceptionDetails.java`: Adicionadas novas constantes de erro para tratar falhas na criação de doação e abrigo.
 
- `AddressCreationFailureException.java`: Criada classe de exceção personalizada para lidar especificamente com falhas durante a criação de endereços.

- `DonationRegisterFailureException.java`: Criada classe de exceção personalizada para lidar especificamente com falhas durante o registro de doações.

- `ShelterCreationFailureException.java`: Criada classe de exceção personalizada para lidar especificamente com falhas durante a criação de abrigos.

**Nota:** A ênfase deste commit está em melhorar o tratamento de erros durante a criação de `Address`, `Donation`, e `Shelter` através do uso de classes de exceção personalizadas.

---

**Commit** d4b3fa7ffc2ac12195fe61b10625098df1fd39b5:

Este commit adiciona documentação para a enumeração `UserProfile`. Esta enumeração fornece os tipos de perfis disponíveis para um usuário, sendo eles 'Doador' e 'Beneficiário'.

**Arquivos Alterados:** `UserProfile.java`

**Alterações:**

- `UserProfile.java`: Adicionada documentação para a enumeração `UserProfile`, descrevendo os tipos de perfis disponíveis para um usuário.

**Nota:** A ênfase deste commit está em adicionar documentação clara para a enumeração `UserProfile`, explicando seu uso e funcionalidade.

---

**Commit** 15c239b2f165c4f5d3014d629dca5cae0547ae59:

Este commit adiciona várias classes de teste para a validação das entidades Address, Donation e Shelter. Os testes incluem a verificação de criação bem-sucedida de instâncias, validação de campos necessários, mudança de atributos e exceções de erro esperadas.

**Arquivos Adicionados:** `ShelterTest.java`, `AddressTest.java`, `DonationTest.java`

**Alterações:**

Criação de testes para as seguintes entidades:

- `ShelterTest.java`: foram adicionados testes para a criação de abrigos, validação do ID do abrigo, alteração do nome e endereço do abrigo e adição de doações ao abrigo.

- `AddressTest.java`: foram adicionados testes para a criação de endereços e validação de campos de endereço.

- `DonationTest.java`: foram adicionados testes para a criação de doações e validação de campos de doação.

**Nota:** A principal ênfase desse commit é adicionar uma ampla cobertura de testes para as entidades de Address, Donation e Shelter, incluindo testes de validação de campo, alteração de atributos e criação de instâncias. 

---

**Commit** 6d2b5ea5b4cef2afe8f3293b445f6e6d9bb64a27:

Este commit introduz novas classes de entidade no domínio principal. Foram adicionadas as classes Shelter, Address e Donation para representar refúgios, endereços e doações, respectivamente. Além disso, foram implementadas verificações para validar os dados de cada entidade antes da criação dos objetos, garantindo a integridade dos dados.

**Arquivos Adicionados:** `Shelter.java`, `Address.java`, `Donation.java`

**Alterações:**

Foram adicionadas as seguintes novas classes de entidade:

- `Shelter.java`: Representa um abrigo na aplicação com atributos para identificação, nome do abrigo, endereço, usuário responsável e uma lista de doações. A validação de dados é feita antes da criação do objeto para garantir a integridade dos dados.

- `Address.java`: Representa um endereço físico que é associado a um abrigo. Os detalhes incluem a rua, o número, o bairro, a cidade, o estado e o CEP.

- `Donation.java`: Representa uma doação que pode ser feita a um abrigo. Tem atributos para identificação única da doação, o nome da doação e a quantidade da doação.

**Nota:** A enfase principal dessa confirmação é o estabelecimento de novas classes de entidade para representar abrigos, endereços e doações. Cada uma dessas novas classes de entidade inclui verificação e validação de dados para garantir a integridade dos dados quando um novo objeto é criado.

---

**Commit** a8f4e148ff4cfd1a4c8b46d7614d6e29fa8a8461:

Este commit adiciona a classe UserFactory para lidar com a criação de objetos do usuário. Além disso, os testes para a entidade de usuário foram atualizados para usar a nova fábrica em vez da criação direta do construtor, proporcionando uma validação mais eficiente do UUID do usuário.

**Arquivos Adicionados:** `UserFactory.java`
**Arquivos Modificados:** `UserTest.java`

**Alterações:**

- `UserFactory.java`: Uma nova classe UserFactory foi adicionada. A classe conta com um método chamado `create`, que aceita parâmetros para nome do usuário, e-mail, perfil de usuário e senha, e retorna um novo objeto Usuário. Isso facilita a construção de objetos de usuário e encapsula a lógica de sua criação em uma única classe.

- `UserTest.java`: Os testes da entidade do usuário foram atualizados para utilizar a nova fábrica. Isso resultou em uma validação mais eficiente do UUID do usuário e uma forma mais limpa de criar objetos de usuário para os testes.

**Nota:** A principal ênfase dessa confirmação é a adição da classe UserFactory para simplificar a criação de objetos de usuário e proporcionar a validação eficiente do UUID do usuário durante a criação de novos objetos de usuário, o que melhorou a qualidade dos testes de usuários.

---

**Commit** 1e4dd6df35c94ab31da36f9a6e9b7749792c5896:

Este commit adiciona a classe ShelterFactory para lidar com a criação de objetos Shelter por meio de um método estático. Além disso, os testes para a entidade Shelter foram atualizados para usar a nova fábrica em vez da criação direta do construtor, proporcionando uma validação mais eficiente do UUID do Shelter.

**Arquivos Adicionados:** `ShelterFactory.java`
**Arquivos Modificados:** `ShelterTest.java`

**Alterações:**

- `ShelterFactory.java`: Uma nova classe ShelterFactory foi adicionada. A classe oferece um método chamado `create`, que aceita parâmetros para o nome do abrigo, o objeto de endereço e o usuário responsável, e retorna um novo objeto Shelter. Isso facilita a construção de objetos de abrigo e encapsula a lógica de criação em uma única classe.

- `ShelterTest.java`: Os testes da entidade Shelter foram atualizados para utilizar a nova fábrica. Isso resultou em uma validação mais eficiente do UUID do abrigo e uma forma mais limpa de criar objetos de abrigo para os testes.

**Nota:** A principal ênfase desta confirmação é a adição da classe ShelterFactory para simplificar a criação de objetos Shelter. Isso melhora a consistência e a facilidade de manutenção do código, ao mesmo tempo em que proporciona uma validação eficaz do UUID durante a criação de novos objetos de abrigo.

---

**Commit** 87c199f6098852153e3af2341bb124fdb8257a33:

Este commit refatora a lógica de validação dos dados do usuário introduzindo o método `checkNotNullAndNotEmptyOrThrowException`. Este método genérico verifica a nulidade e o vazio de objetos e strings, respectivamente, eliminando a repetição de código, simplificando a lógica de validação e aumentando a clareza e a concisão do código.

**Arquivos Alterados:** `User.java`

**Alterações:**

- `User.java`: Refatorado o método `validateData()`, removendo a verificação explícita de nulidade e vazio de cada campo. Essas verificações foram encapsuladas e centralizadas no novo método `checkNotNullAndNotEmptyOrThrowException()`, que é utilizado para validação. Agora, as exceções `UserCreationFailureException` são lançadas a partir deste método centralizado.

**Nota:** A ênfase principal deste commit é melhorar a qualidade do código, reduzindo a redundância e aumentando a legibilidade e manutenção através da introdução de uma função de validação centralizada que poder ser reutilizada para várias verificações de validação.

---

**Commit** b2f376bae6579f894798cdd3b8bf3085f364f860:

Este commit introduz a classe `ValidationUtils` para abstrair uma validação genérica de objeto nulo ou vazio (se for uma instância de String). A validação é então aplicada nas classes `User` e `Shelter`, substituindo a lógica de validação de objeto existente por um único método de validação centralizado e reutilizável.

**Arquivos Alterados:** `Shelter.java`, `User.java`, `ExceptionDetails.java`
**Arquivos Adicionados:** `ValidationUtilsException.java`

**Alterações:**

- `Shelter.java`: Substituídas as verificações de nulidade e vazio por chamadas ao método `ValidationUtils.checkNotNullAndNotEmptyOrThrowException()`.
- `User.java`: Análogo à classe `Shelter.java`, as verificações de nulidade e vazio foram substituídas por chamadas ao método `ValidationUtils.checkNotNullAndNotEmptyOrThrowException()`.
- `ExceptionDetails.java`: Adicionado um novo tipo de erro `EXCEPTION_TYPE_NOT_THROWN`.
- `ValidationUtilsException.java`: Nova classe de exceção lançada quando um erro relacionado a uma exceção personalizada não pode ser lançada.

**Nota:** O principal objetivo deste commit é tornar o código mais eficiente e fácil de manter, introduzindo um mecanismo de validação centralizado e reutilizável para objetivos comuns de validação, como verificar a nulidade de um objeto ou se uma string está vazia.

---

**Commit** c3136efe49172d352450ddd081386aa44d2fb1c4:

Neste commit, foram adicionados comentários de documentação completos para as classes `UserFactory` e `ShelterFactory`, melhorando a compreensão e a legibilidade de seu uso.

**Arquivos Alterados:** `UserFactory.java`, `ShelterFactory.java`

**Alterações:**

- `UserFactory.java`: Adicionada documentação clara e abrangente que descreve a função da classe, seu funcionamento e a importância de seu método de criação.
- `ShelterFactory.java`: Análogo à classe `UserFactory`, foram adicionados comentários de documentação detalhados que descrevem a função da classe e o uso de seu método de criação.

**Nota:** Este commit se concentra na melhoria da legibilidade e na facilitação da manutenção do código através da adição de comentários explicativos completos nas classes `UserFactory` e `ShelterFactory`.

---

**Commit** 091c6eb82e4697dbe5ba101ccbec70de48d749cb:

Este commit adiciona testes de unidade para a classe `ValidationUtils` para assegurar a eficácia de seus métodos. Houve uma pequena limpeza, removendo importações desnecessárias e simplificando o lançamento de exceções.

**Arquivos Alterados:** `ValidationUtils.java`

**Arquivo Adicionado:** `ValidationUtilsTest.java`

**Alterações:**

- `ValidationUtils.java`: Implementada a simplificação no lançamento de exceções e removidas importações desnecessárias.
- `ValidationUtilsTest.java`: Adicionados testes de unidade para todos os métodos públicos na classe `ValidationUtils`. Esses testes verificam a eficácia e a precisão dos métodos de validação fornecidos pela classe.

**Nota:** A ênfase principal deste commit é melhorar a robustez do código, garantindo que o comportamento esperado da classe de utilidade da `ValidationUtils` esteja correto por meio de testes de unidade completos e abrangentes.

---

**Commit** 14f567111c672a53dd7b9a8caa980ac09dc3ca6b:

Este commit adiciona o `UserService` com operações CRUD básicas. Foram adicionadas funções para criar, recuperar, atualizar e excluir usuários. Também foram implementados métodos para alterar a senha e o nome de usuário. A estrutura inclui validações de dados e tratamento de exceções personalizadas para situações de erros específicos durante essas operações.

**Arquivos Adicionados:** `UserRepository.java`, `ExceptionDetails.java`, `UserServiceFailureException.java`, `RepositoryContract.java`, `UserService.java`

**Alterações:**

- `UserRepository.java`: Interface do repositório de usuário criada para definir operações específicas para usuários.
- `ExceptionDetails.java`: Enumeração para detalhar as exceções que ocorreram. Adicionada `USER_MANIPULATION_ERROR` e `SHELTER_MANIPULATION_ERROR` como novos tipos de exceção.
- `UserServiceFailureException.java`: Exceção personalizada para falha no `UserService` durante a manipulação do usuário.
- `RepositoryContract.java`: Interface de contrato de repositório genérica que define as operações CRUD básicas.
- `UserService.java`: Implementação do contrato do serviço `UserService` para fornecer operações CRUD básicas.

**Nota:** O foco deste commit é a implementação do `UserService` com operações CRUD básicas. Também aborda a captura de situações de erro específicas durante essas operações e adiciona validações para garantir a integridade dos dados do usuário.

---

**Commit** d815d980246582fcb489c47d2492d8dfa6952cc7:

Este commit adiciona testes para exceções de criação de usuário no `UserServiceTest.java`. Os testes adicionados verificam cenários em que a criação do usuário pode falhar devido à falta de valor ou valor vazio para os campos de nome de usuário, e-mail, perfil de usuário e senha. Esses testes podem ajudar a tornar o código mais robusto, contribuindo para um tratamento adequado das exceções.

**Arquivo Alterado:** `UserServiceTest.java`

**Alterações:**

- Adicionados vários testes no arquivo `UserServiceTest.java`. Esses testes lidam com cenários em que a criação do usuário pode falhar. Os campos verificados são o nome do usuário, e-mail, perfil do usuário e senha.
- Se o valor de qualquer um desses campos for nulo ou vazio, uma exceção `UserCreationFailureException` é lançada.
- Durante cada teste, é verificado que o método `save` do repositório de usuários nunca é chamado (verifica-se usando `verify(this.userRepository, never()).save(any(UserContract.class));`).
- Após a exceção ser lançada, cada teste verifica se a exceção lançada é de fato uma instância de `UserCreationFailureException`.

**Nota:** Este commit foca principalmente em aprimorar a robustez do código, garantindo que as exceções de falha na criação do usuário sejam adequadamente tratadas.

---

**Commit** 943ef24af1be3c7cb3f58c752280c509105e6ce3:

Este commit adiciona uma validação para garantir que os identificadores de usuário sejam UUIDs válidos. Isso é feito através de um novo método, `validateUserId`, que é chamado sempre que um userId é usado. Testes relacionados à nova implementação também foram adicionados.

**Arquivos Alterados:** `UserService.java`, `UserServiceTest.java`

**Alterações:**

- Em `UserService.java`, foi adicionado um novo método `validateUserId`. Este método chama `ValidationUtils.checkNotNullAndNotEmptyOrThrowException` para garantir que userId não seja nulo ou vazio. Depois disso, tenta validar userId como um UUID válido usando `UuidUtils.isValidUUID`.
- `validateUserId` é chamado em múltiplos lugares no `UserService.java`, como `getUser`, `changePassword` e `changeUserName`.
- Em `UserServiceTest.java`, foram adicionados novos testes para verificar se a exceção `UserServiceFailureException` é lançada quando um userId inválido é passado para `getUser`, `changePassword` e `changeUserName`.

**Nota:** Este commit se concentrará principalmente na validação de userId. O código agora garante que todos os userIds no sistema sejam UUIDs válidos para evitar problemas futuros.

---

**Commit** 7b1e6662913d80a4dd27e759dfddd3eb4842911b:

Este commit adiciona um novo conjunto de métodos ao `Shelter` que retornam todas as doações associadas ao abrigo. Nos testes, a maneira de obter doações do abrigo foi ajustada para usar o novo método criado, em vez de refletir diretamente na variável.

**Arquivos Alterados:** `Shelter.java`, `ShelterContract.java`, `ShelterTest.java`

**Alterações:**

- Um novo método `getDonations` foi adicionado à classe `Shelter`. Este método retorna todas as doações associadas a este abrigo.
- O método `getDonations` também foi adicionado à interface `ShelterContract`. Ele define que um abrigo deve ser capaz de retornar todas as doações a ele associadas.
- Em `ShelterTest`, o código foi alterado para utilizar o novo método `getDonations` quando se quer verificar as doações de um abrigo. Anteriormente, isso era feito através de reflexão, que é uma prática menos segura e mais propensa a erros.

**Nota:** Este commit foca principalmente em adicionar um novo método para obter todas as doações associadas a um abrigo. Este é um passo para tornar o código mais seguro e legível.

---

**Commit** 389a611d105272d3396022e7d8ec6b16816d85e5:

Este commit adiciona novas classes para manipular abrigos, o que inclui a criação de classes de repositório, serviço e exceção, juntamente com testes associados. Operações de gerenciamento incluem criar um abrigo, alterar o nome e o endereço de um abrigo, adicionar uma doação, e obter detalhes de um abrigo. Além disso, novas exceções foram adicionadas para lidar com erros que podem ocorrer durante a manipulação de abrigos.

**Arquivos Adicionados:** `ShelterRepository.java`, `ShelterServiceFailureException.java`, `ShelterService.java`

**Alterações:**

- `ShelterRepository.java`: Esta interface define o contrato para um repositório que persiste e recupera as entidades Shelter.
- `ShelterServiceFailureException.java`: Esta é uma classe de exceção personalizada que estende a RuntimeException. É usada especialmente para lidar com erros que ocorrem durante a criação de um abrigo.
- `ShelterService.java`: A classe de serviço ShelterService é responsável pelas operações de negócios relacionadas a abrigos. Estas operações incluem criação de abrigos, busca por abrigos e alterações nos atributos dos abrigos.

**Nota:** Este commit se concentra na adição de novas classes e métodos para manipular abrigos, que é um passo essencial para o gerenciamento de abrigos no sistema.

---

**Commit** 03c8a914696ad46ef59581396a9415c4827163e3:

Este commit atualiza os javadocs em vários arquivos, mudando as tags de versão para indicações de "since". Isso melhora a documentação e reflete de maneira mais apropriada que as classes ou métodos são considerados como parte do projeto desde a versão indicada.

**Arquivos Alterados:**   
`CorsConfig.java`, `OpenApiConfig.java`, `WebSecurityConfig.java`, `Shelter.java`, `ShelterContract.java`, `Address.java`, `Donation.java`, `ShelterFactory.java`, `ShelterRepository.java`, `User.java`, `UserContract.java`, `UserProfile.java`, `UserFactory.java`, `UserRepository.java`

**Alterações:**

- Javadocs em arquivos foram alterados para usar a tag "@since" em vez de "@version" para indicar desde qual versão essas classes ou métodos estão disponíveis.

**Nota:** A principal mudança deste commit foi para atualizar a documentação das classes, o que melhora a manutenibilidade e a compreensibilidade do código para os futuros mantenedores.

---

**Commit** 3612f0bde4ae449b32256b2ae8bd92b5f2c8ea8d:

Este commit adiciona um novo método privado para validar o ID do usuário na classe `UserService`. Este é um método importante que é chamado antes de várias outras operações. Foram feitas também algumas pequenas alterações de formatação para melhor alinhamento dos parâmetros dos métodos.

**Arquivos Alterados:** `UserService.java`

**Alterações:**

- Adicionado um novo método privado `validateUserId` para validar se o ID do usuário fornecido é válido. 
- Realizadas pequenas alterações de formatação para alinhar melhor os parâmetros dos métodos.

**Nota:** O principal foco deste commit é melhorar a validação e a qualidade do código através da adição de verificação de ID do usuário e ajuste de formatação.

---

**Commit** 7e5e43c5d680fdcd524fac6a41f057040652d497:

Este commit refatora as importações nos testes `ShelterServiceTest` e `UserServiceTest`. As importações desnecessárias foram removidas e as importações relacionadas ao Assertions foram reorganizadas para melhorar a clareza. Algumas classes não utilizadas foram também removidas para manter o código limpo.

**Arquivos Alterados:** `ShelterServiceTest.java`, `UserServiceTest.java`

**Alterações:**

- Remoção de importações desnecessárias.
- Reorganização das importações de Assertions para melhor clareza e legibilidade.
- Remoção de classes que não estavam sendo utilizadas.

**Nota:** O principal objetivo deste commit é melhorar a legibilidade e a organização do código, mantendo-o limpo e conciso.

---

**Commit** da7c7b55874ea6d4d05c68b1c28db8a7f6122b10:

Este commit adiciona várias entidades de banco de dados que representam Donation, Shelter e Address, bem como um enum de perfil de usuário. As entidades de banco de dados são utilizadas para modelar a estrutura do banco de dados e facilitar as operações de banco de dados, enquanto o enum de perfil de usuário é utilizado para categorizar os usuários em diferentes perfis.

**Arquivos Adicionados:** 
- `UserProfileType.java`
- `AddressEntity.java`
- `DonationEntity.java`
- `ShelterEntity.java`

**Alterações:**

- Adicionada a enumeração `UserProfileType` com os perfis disponíveis para um usuário: 'Doador' e 'Beneficiário'.
- Adicionada a classe `AddressEntity` que representa um objeto de endereço no banco de dados mapeado para a tabela "address"
- Adicionada a classe `DonationEntity` que representa um objeto de doação no banco de dados mapeado para a tabela "donations".
- Adicionada a classe `ShelterEntity` que representa um objeto de abrigo no banco de dados mapeado para a tabela "shelters".

**Nota:** Este commit foca na definição da estrutura básica do banco de dados e na categorização de usuários em diferentes perfis de usuário.

---

**Commit** eb77a61034db8d631f2f903c7a2fa6f7db95dd8e:

Este commit atualiza os nomes dos métodos do repositório que estão sendo usados no `ShelterServiceTest` e `UserServiceTest`. As mudanças incluem a alteração de `save` para `persist`, `findById` para `findEntityById`, e a atualização do nome dos repositórios. Os testes foram também atualizados para refletir essas mudanças.

**Arquivos Alterados:** 
- `ShelterEntity.java`
- `ShelterRepository.java` renomeado para `ShelterContractRepository.java`
- `UserRepository.java` renomeado para `UserContractRepository.java`
- `RepositoryContract.java`
- `ShelterService.java`

**Alterações:**

- Remoção do comentário de `UserEntity responsibleUser` em `ShelterEntity.java`.
- Alteração do nome do `ShelterRepository` para `ShelterContractRepository` em `ShelterService.java` e no próprio arquivo `ShelterRepository.java`.
- Alteração do nome do `UserRepository` para `UserContractRepository` no próprio arquivo `UserRepository.java`.
- Atualização dos nomes dos métodos do repositório de acordo com as novas alterações na classe `RepositoryContract.java`.
- Atualização dos métodos em `ShelterService.java` para usar os novos nomes dos métodos do repositório.

**Nota:** A principal ênfase deste commit é atualizar a nomenclatura dos métodos do repositório para melhorar a clareza e consistência do código.

---

**Commit** 5bb4b9a6dff4553c27f0fd548dd06db65ac19eb1:

Este commit adiciona novas classes de mapeamento para lidar com a conversão de entidades de persistência em objetos de domínio. Ele também inclui a adição de novas classes de exceções personalizadas para fornecer mensagens de erro mais detalhadas durante a manipulação dos dados das entidades.

**Arquivos Adicionados:** 
- `ExceptionDetails.java`
- `ConstructorDefaultUndefinedException.java`
- `MapperFailureException.java`
- `ShelterEntityFailuresException.java`
- `BuilderMapper.java`

**Alterações:**

- Adicionada a classe `ExceptionDetails.java` que é uma enumeração que define várias mensagens de exceções.
- Adicionada a classe `ConstructorDefaultUndefinedException.java` que é uma exceção personalizada lançada quando não é definido um construtor padrão.
- Adicionada a classe `MapperFailureException.java` que é uma exceção personalizada para gerenciar falhas durante o processo de mapeamento.
- Adicionada a classe `ShelterEntityFailuresException.java` que é uma exceção personalizada lançada quando ocorre uma falha em uma operação relacionada ao `ShelterEntity`.
- Adicionada a classe `BuilderMapper.java` que fornece métodos para mapear os campos de um objeto fonte para os campos de uma classe destino.

**Nota:** A principal ênfase deste commit é adicionar classes de mapeamento e exceção personalizadas para manipulação consistente de conversões de entidade de persistência para objetos de domínio e gerenciamento de erros durante essas operações. Isso aprimora a robustez e a transparência do código, fornecendo mensagens de erro mais informativas.

---

**Commit** 69804a5fca3a7c420a4de00b0322a5f804ee137d:

Este commit adiciona a classe `ShelterRepository` que é responsável pela persistência de dados relacionados a abrigos. Implementa operações CRUD e adiciona suas próprias operações básicas, como encontrar por ID, buscar todas, persistir e deletar. Mecanismos de mapeamento foram utilizados para desacoplar a conversão entre entidades JPA e objetos de domínio.

**Arquivos Adicionado:** 
- `ShelterRepository.java`

**Alterações:**

- Adicionada a classe `ShelterRepository` que é responsável pela persistência de dados relacionados a abrigos. Ela implementa operações CRUD e implementa também suas próprias operações básicas, como encontrar por ID, buscar todas, persistir e deletar. 

**Nota:** A principal ênfase deste commit é a criação do repositório `ShelterRepository` que é crucial para a persistência de dados relacionados a abrigos. Os mecanismos de mapeamento utilizados auxiliam na conversão entre entidades JPA e objetos de domínio, promovendo um código mais limpo e desacoplado.

---

**Commit** 5f009c3a9d4327d5576865bd6d475e9c4fd7f29b:

Este commit adiciona exceções personalizadas para falhas no endereço e usuário, além de  estratégias de mapeamento para converter entidades de abrigo para entidades de domínio. Agora, as doações associadas a um abrigo são corretamente mapeadas e atribuídas ao objeto de abrigo durante a construção. A cobertura do teste foi estendida para abordar esses novos cenários.

**Arquivos Adicionados:** 
- `AddressEntityFailuresException.java`
- `UserEntityFailuresException.java`

**Arquivos Alterados:** 
- `ExceptionDetails.java`
- `MapperStrategy.java`
- `ShelterMapper.java`

**Alterações:**

- Adicionadas as exceções `AddressEntityFailuresException` e `UserEntityFailuresException`.
- Atualizados os detalhes de exceção em `ExceptionDetails.java` para fornecer mensagens de erro mais específicas.
- Atualizada a estratégia de mapeamento do objeto `MapperStrategy` para refletir a alteração de 'origem' para 'source'.
- Atualizado o `ShelterMapper` para incluir o mapeamento de doações associadas ao abrigo.

**Nota:** Este commit incide sobre a adição de exceções personalizadas para fornecer mensagens de erro mais específicas e úteis. Além disso, melhorou o mapeamento de objetos para cobrir associações complexas, como mapear doações para um abrigo.

---

**Commit** ea36a07eb5bee11bdae27f783ffcb318faa0faf5:

Neste commit foram implementados as verificações de dados em `UserMapper` para garantir que os dados não sejam nulos ou vazios antes das operações de mapeamento. Também foram adicionados testes unitários correspondentes para essas validações. Além disso, a exceção `ConstructorDefaultUndefinedException` foi expandida para aceitar uma mensagem personalizada.

**Arquivo Alterados:** 
- `ConstructorDefaultUndefinedException.java`
- `UserMapper.java`

**Arquivo Adicionado:** 
- `UserMapperTest.java`

**Alterações:**

- Adicionado no `ConstructorDefaultUndefinedException` um construtor que permite definir uma mensagem de erro personalizada.
- Implementado o método `validateData` em `UserMapper` que valida se os dados fornecidos são nulos ou vazios, lançando uma exceção se qualquer uma dessas condições for verdadeira.
- Adicionado `UserMapperTest` para testar o mapeamento de um `UserEntity` para um `User` e vice-versa, além de testar o lançamento de exceções quando os dados são nulos ou vazios.

**Nota:** Este commit foca em garantir a qualidade dos dados durante o mapeamento entre `UserEntity` e `User`, adicionando validações de dados e expandindo as capacidades de tratamento de exceções.

---

**Commit** 8e4e95a8ca320f7bf75043d85aac35a8db46e1c3:

Neste commit, foram adicionados os adaptadores `AddressMapper` e `UserEntityMapper` para converter `AddressEntity` em objetos `Address` e objetos `User` em entidades `UserEntity`, respectivamente. Testes de unidade correspondentes foram adicionados para assegurar a funcionalidade correta do mapeamento. Atualizações foram feitas no `BuilderMapper` para permitir mais funcionalidades de mapeamento.

**Novos Arquivos:** 
- `AddressMapper.java`
- `UserEntityMapper.java`

**Arquivo Alterado:** 
- `BuilderMapper.java`

**Alterações:**
- Implementado o `AddressMapper` para converter objetos `AddressEntity` em objetos `Address`, incluindo a validação de dados e gerenciamento de exceções.
- Implementado o `UserEntityMapper` para converter objetos `User` em entidades `UserEntity`.
- O `BuilderMapper` foi atualizado para gerenciar a criação de instâncias de classe com construtores não-padrão.

**Nota:** Este commit se concentra em melhorar as capacidades de mapeamento do projeto, adicionando novos adaptadores de mapeamento e expandindo as funcionalidades do `BuilderMapper`.

---

**Commit** 1c8e3d09220490f41b5533191467bd689efe6b9e:

Neste commit, a classe `Address` foi atualizada para incluir um campo de ID. As checagens de validação de campo foram atualizadas para incluir checagens para IDs válidas e foram adicionadas as exceções apropriadas. Também foram adicionados teste para confirmar a funcionalidade da criação do ID do endereço e do tratamento de exceções.

**Arquivos alterados:** 
- `AddressMapper.java`
- `Address.java`
- `AddressCreationFailureException.java`
- `AddressTest.java` 

**Alterações:**
- Adicionado campo ID na classe `Address` e atualizamos o construtor e os métodos de validação correspondentes.
- O método `mapFrom` no `AddressMapper` foi atualizado para mapear o ID do `AddressEntity` para o `Address`.
- `AddressCreationFailureException` foi extendido para suportar exceções causadas por IDs inválidos.
- `AddressTest` foi atualizado para testar a nova funcionalidade de ID.

**Nota:** Este commit foca na aprimoração das funcionalidades do objeto `Address` acrescentando um campo de ID e adicionando a lógica de validação apropriada.

---

**Commit** 2df853b611f0d6a14cb399807cd3af6e20a18808:

Este commit adiciona a validação de ID na entidade `Donation`. Agora, a entidade é validada para garantir que o ID seja um UUID válido, também foram adicionados testes unitários para cobrir esses novos casos de erro. Além disso, o código foi refatorizado para utilizar a utilidade de validação para checagens de valores nulos e vazios.

**Arquivos alterados:** 
- `Donation.java`
- `DonationRegisterFailureException.java`
- `DonationTest.java`

**Alterações:**
- Adicionado campo ID na classe `Donation` com validação para garantir que seja um UUID válido.
- `DonationRegisterFailureException` foi ampliado para suportar exceções causadas por IDs inválidos.
- Testes adicionados em `DonationTest` para verificar o comportamento com IDs inválidos.
- Refatorado o código para usar `ValidationUtils` para verificações de nulidade e vazio.

**Nota:** Este commit foca na melhoria das funcionalidades do objeto `Donation`, adicionando um campo de ID com a lógica de validação apropriada e refatorando o código para reuso.

---

**Commit** 6e1641e950bd467f7d74afe434e1d060dd7cbf3a:

Este commit atualiza as classes `ShelterTest`, `ShelterServiceTest`, `ShelterMapper` e `ShelterMapperTest` para substituir a criação de objetos `Address` e `Donation` com novos construtores que incluem IDs. Essas alterações ajudam a evitar a criação de objetos duplicados e melhorar a identificação de objetos durante a execução do código.

**Arquivos Alterados:** 
- `ShelterMapper.java`
- `ShelterMapperTest.java`
- `ShelterTest.java`
- `ShelterServiceTest.java`

**Alterações:**

- No `ShelterMapper.java`, o mapeamento foi alterado para os construtores com ID em vez dos construtores sem ID para os objetos `Address` e `Donation`.
- No `ShelterMapperTest.java`, o mapeamento foi atualizado para usar construtores com IDs para `Address` e `Donation`.
- No `ShelterTest.java`, foi atualizada a geração de objetos `Address` e `Donation` para modelos que aceitam IDs.
- No `ShelterServiceTest.java`, foi atualizado o modelo de `Address` para um modelo que aceita ID.

**Nota:** Este commit se concentra na atualização do mapeamento e testes de unidades para se alinhar com mudanças recentes nos construtores de `Address` e `Donation`.

---

**Commit** 4e1819e1423c4780e2fe5a3fd9f55adab902b43f:

Este commit atualiza a classe `UserEntityMapper` para incluir uma documentação mais detalhada sobre seu uso e funcionamento. Também foi removida a exceção 'ConstructorDefaultUndefinedException', tornando o código mais limpo e legível.

**Arquivos Alterados:** 
- `UserEntityMapper.java`

**Alterações:**

- A documentação foi expandida e melhorada na classe `UserEntityMapper`, incluindo informações mais detalhadas sobre o propósito da classe e o uso de seus métodos.
- A exceção `ConstructorDefaultUndefinedException` foi removida como parte dos catchs do método de mapeamento, o que torna o código mais legível.

**Nota:** A ênfase principal neste commit foi na melhoria da documentação de classe e na limpeza do código para melhor legibilidade e manutenibilidade.

---

**Commit** 9dc93888fd8a7bee62ada68fb5eafa9e2b2ff1e8:

Este commit remove a anotação `@GeneratedValue` dos IDs em `AddressEntity.java` e `DonationEntity.java`. A partir de agora, a geração do UUID será responsabilidade do código que cria uma instância dessas entidades.

**Arquivos Alterados:** 
- `AddressEntity.java`
- `DonationEntity.java`

**Alterações:**

- As anotações `@GeneratedValue` foram removidas dos IDs em `AddressEntity.java` e `DonationEntity.java`. Isso implica que o próprio código agora terá a responsabilidade de gerar os UUIDs quando criar instâncias dessas entidades.

**Nota:** Este commit se concentra em transformar a geração de UUIDs para as entidades `Address` e `Donation` em uma responsabilidade do código que cria instâncias dessas entidades, em vez de confiar na auto geração pelo JPA.

---

**Commit** caf02cfe13f195e5edbd20daa2c7a3d43bee9e18:

Este commit introduz novas classes de mapeamento para gerenciar a conversão entre entidades de doação e objetos de domínio. Essas classes recém-criadas são `DonationMapper` e `DonationEntityMapper`. Configurações de testes unitários correspondentes também foram implementadas para garantir a funcionalidade correta dessas classes. Além disso, este commit também apresenta a classe `DonationFactory` para lidar com a criação de instâncias de doação.

**Arquivos Adicionados:** 
- `DonationEntityMapper.java`
- `DonationMapper.java`
- `DonationFactory.java`
- `DonationEntityMapperTest.java`
- `DonationMapperTest.java`
- `DonationFactoryTest.java`

**Alterações:**

- A nova classe `DonationEntityMapper` foi adicionada, que implementa o mapeamento de objetos de domínio `Donation` para entidades `DonationEntity`.
- A nova classe `DonationMapper` foi adicionada, que implementa o mapeamento de entidades `DonationEntity` para objetos de domínio `Donation`.
- A nova classe `DonationFactory` foi adicionada, que fornece uma função de fábrica para a criação de instâncias `Donation`.
- Novos testes unitários foram adicionados para `DonationEntityMapper`, `DonationMapper` e `DonationFactory` para garantir que as classes implementem a funcionalidade correta.

**Nota:** Este commit visa melhorar a manutenção e a postoerior manutenção do código, separando a lógica de mapeamento de `Donation` e `DonationEntity` em classes separadas e não espalhado por todo o código.

---

**Commit** 0ab93e8348434a81e2970f5fdfd22c7c86315d10:

Este commit introduz o mapeador de entidades de endereço, testes para o mapeador e uma fábrica de endereços. O mapeador é usado para traduzir as entidades de endereço do domínio para as entidades de endereço que podem ser armazenadas no banco de dados, e vice-versa. A fábrica de endereço, por sua vez, fornece um meio centralizado e simplificado para criar novas entidades de endereço.

**Arquivos Adicionados:** 
- `AddressEntityMapper.java`
- `AddressFactory.java`
- `AddressEntityMapperTest.java`

**Alterações:**

- A nova classe `AddressEntityMapper` foi adicionada, que implementa o mapeamento de objetos de domínio `Address` para entidades `AddressEntity`.
- A nova classe `AddressFactory` foi adicionada, que fornece uma função de fábrica para a criação de instâncias `Address`.
- Novos testes unitários foram adicionados para `AddressEntityMapper` para garantir que a classe implemente a funcionalidade correta.

**Nota:** Este commit visa melhorar a manutenção e posterior manutenção do código, separando a lógica de mapeamento de `Address` e `AddressEntity` em classes separadas e não espalhado por todo o código.

---

**Commit** a74194682c4635aeaa9e7fe03ea57821f6554cf5:

Este commit introduz a classe ShelterEntityMapper, que é responsável pelo mapeamento da entidade de abrigo. A implementação inclui funções para mapear de entidades para objetos de domínio e vice-versa, bem como tratamento de exceções. Além disso, inclui testes unitários extensivos para verificar a funcionalidade do mapeador.

**Arquivos Adicionados:** 
- `ShelterEntityMapper.java`
- `ShelterEntityMapperTest.java`

**Alterações:**

- A nova classe `ShelterEntityMapper` foi adicionada, que implementa o mapeamento de objetos de domínio `Shelter` para entidades `ShelterEntity`.
- Novos testes unitários foram adicionados para `ShelterEntityMapper` para garantir que a classe implemente a funcionalidade correta.

**Nota:** Este commit visa melhorar a manutenção e posterior manutenção do código, separando a lógica de mapeamento de `Shelter` e `ShelterEntity` em classes separadas e não espalhado por todo o código.

---

**Commit** a2ba3d880cdfb3c38e9a0c1e78e72f993e69a45d:

Este commit atualiza a classe ShelterMapper para melhorar a clareza e robustez do mapeamento de ShelterEntity para Shelter. A verificação de objeto source não nulo foi adicionada e o tratamento de exceções foi aprimorado. As mudanças nos testes acrescentam testes parametrizados e atualizam verificações de erro.

**Arquivos Alterados:** 
- `ShelterMapper.java`
- `ShelterMapperTest.java`

**Alterações:**

- Na classe `ShelterMapper.java`, a verificação de objeto source não nulo foi adicionada e o tratamento de exceções foi aprimorado para melhor lidar com erros de runtime. 
- Em `ShelterMapperTest.java`, foram adicionados testes parametrizados e atualizadas as verificações de erro de acordo com as mudanças implementadas em `ShelterMapper.java`.

**Nota:** Essas alterações visão o fortalecimento e a clareza do mapeamento de entidades, além de aumentar a cobertura de testes para situações diversas de erro.

---

**Commit** fd8c2a0d83d5471aef6aee0d7fb666f67e393bcd:

Este commit atualiza o mapeamento e a persistência de entidades no reposítorio `ShelterRepository`. Foram feitas alterações significativas no método `findEntityById`, removendo a captura de exceções e substituindo pela chamada direta ao método `mapFrom`. Adicionalmente, foi implementada a lógica de persistência, que garante a não nulidade de uma entidade antes de sua persistência. A entidade é mapeada para o formato correto antes de ser persistida.

**Arquivos Alterados:** 
- `ShelterRepository.java`, `AddressEntity.java`, `DonationEntity.java`, `Donation.java`, `ShelterEntityMapperTest.java`, `ShelterTest.java`, `DonationTest.java`

**Alterações:**

- As anotações `GeneratedValue` e `GenerationType` foram removidas dos arquivos `AddressEntity` e `DonationEntity`.
- No repositório `ShelterRepository`, a chamada ao método `mapFrom` foi direcionada para a classe `ShelterMapper` ao invés da interface `MapperStrategy`.
- Foram implementados detalhamentos no javadoc do método de persistência do `ShelterRepository`.
- A lógica de verificação de que a entidade a ser persistida não é nula foi implementada no handling de persistência em `ShelterRepository`, antes da chamada ao método save do `CrudRepository`.
- O método de persistência em `ShelterRepository` chama o `mapFrom` da classe `ShelterEntityMapper` para transformar a entidade em `Shelter`.
- As propriedades no objeto `Donation` foram alteradas para imutáveis (final).
- O tratamento de exceção `MapperFailureException` foi removido de `ShelterRepository` e substituído pela chamada direta ao método `mapFrom`.

**Nota:** A principal ênfase desta confirmação é melhorar a lógica de persistência e mapeamento no repositório `ShelterRepository`, ao mesmo tempo que melhora a imutabilidade dos objetos `Donation`.

---

**Commit** c181f2b97c512de00e49bdb9bc619b0670bcf255:

Este commit adiciona testes de integração para o repositório `ShelterRepository`. Os testes abordam todas as operações básicas do repositório Shelter, incluindo recuperação, persistência e exclusão de entidades Shelter. Além disso, validações e exceções são testadas para garantir a robustez da implementação.

**Arquivos Alterados:** `ShelterRepository.java`, `ShelterRepositoryIntegrationTest.java` (novo arquivo de teste)

**Alterações:**

- `ShelterRepository.java`: Foi adicionada uma verificação para garantir que a lista de entidades não seja nula ou vazia no método `mapEntityList`.
- `ShelterRepositoryIntegrationTest.java`: Este é um novo arquivo que inclui vários testes de integração projetados para validar as várias funcionalidades em `ShelterRepository`, incluindo a recuperação, persistência e exclusão de entidades, bem como os campos e associações relacionados.

**Nota:** A principal ênfase desta submissão é aprimorar a validade e integridade dos testes implementados para o repositório `ShelterRepository`.

---

**Commit** 325fc20c528eab9b32d7d89df1a5e85e28f87a91:

Este commit altera o método de validação de dados `checkNotNullAndNotEmptyOrThrowException` para `validateNotNullOrEmpty` em várias partes do código, simplificando a verificação dos dados e tornando o código mais legível e conciso. Agora, há apenas um passo para validação de dados, economizando tempo de execução.

**Arquivos Alterados:** `AddressEntityMapper.java`, `AddressMapper.java`, `DonationEntityMapper.java`, `DonationMapper.java`, `ShelterEntityMapper.java`, `ShelterMapper.java`, `UserEntityMapper.java`, `UserMapper.java`, `ShelterRepository.java`, `Shelter.java`

**Alterações:**

- O método de validação foi substituído em todos os arquivos alterados, substituindo `checkNotNullAndNotEmptyOrThrowException` por `validateNotNullOrEmpty`.

**Nota:** A principal ênfase deste commit é simplificar o processo de validação de dados, unindo a verificação de nulidade e vazio em um único método e tornando o código mais legível e conciso.

---

**Commit** 70d2f448e7e970aeaea60c32f2713f781d669dae:

Este commit adiciona a funcionalidade de pesquisa de usuário por e-mail. Isso inclui a criação de um novo endpoint para recuperar os detalhes de um usuário com base em seu e-mail, bem como a criação de novas classes de DTO para mapear a entidade do usuário e a criação de um novo serviço para executar a pesquisa do usuário. Além disso, foram adicionados testes unitários para garantir que o novo endpoint funcione conforme esperado.

**Arquivos Adicionados:** `UserEntityDTO.java`, `UserRepository.java`, `UserEntityService.java`, `UserEntityServiceImpl.java`, `UserRepositoryTest.java`

**Alterações:**

- `UserEntityDTO.java`: Classe modelo DTO para um usuário. Contém informações básicas sobre um usuário, incluindo o id do usuário, o nome do usuário, o e-mail e o tipo de perfil do usuário.
- `UserRepository.java`: Adicionada uma nova funcionalidade para recuperar um usuário do repositório pelo e-mail.
- `UserEntityService.java`: Interface do serviço de entidade de usuário criada para definir a operação de busca de usuário pelo e-mail.
- `UserEntityServiceImpl.java`: Implementação da interface de serviço de entidade de usuário. A nova implementação inclui um método para recuperar usuário pelo e-mail.
- `UserRepositoryTest.java`: Testes unitários para a funcionalidade de busca de usuário pelo e-mail.

**Nota:** Este commit foca na adição de uma nova funcionalidade que permite a busca de um usuário por e-mail. Esta implementação envolve não apenas a criação do endpoint de busca, mas também a implementação dos métodos de serviço correspondentes e a execução de testes para garantir que a funcionalidade esteja funcionando conforme esperado.

---

**Commit** 39b658255cf4391c1e5b414cfab78d3d56732a2b:

Este commit inclui novos DTOs para endereço e criação de abrigos, além de repositórios para endereços e doações. Também foram criadas classes para lidar com a resposta à criação de um abrigo e a solicitação de criação de um abrigo.

**Arquivos Adicionados:** `AddressDTO.java`, `AddressRepository.java`, `DonationRepository.java`, `ShelterCreationRequest.java`, `ShelterCreatedResponse.java`

**Alterações:**

- `AddressDTO.java`: DTO criado para representar um endereço, contém informações básicas de endereço, como rua, número, bairro, cidade, estado e CEP.
- `AddressRepository.java`: Repositório criado para lidar com operações de banco de dados para o DTO de endereço.
- `DonationRepository.java`: Repositório criado para lidar com operações de banco de dados para a entidade de doação.
- `ShelterCreationRequest.java`: Classe de solicitação criada para lidar com a solicitação de criação de um abrigo. Contém informações essenciais para a criação de um abrigo, incluindo o nome do abrigo, um AddressDTO representando o endereço do abrigo, e o e-mail do usuário responsável pelo abrigo.
- `ShelterCreatedResponse.java`: Classe de resposta criada para lidar com a resposta da criação de um abrigo. Contém informações sobre a resposta da criação de um abrigo, incluindo o id do novo abrigo, o nome do abrigo, o endereço do abrigo, e o usuário responsável pela criação do abrigo.

**Nota:** Este commit foca na preparação dos componentes necessários para a criação e resposta de abrigos. Isso inclui a adição de novos DTOs, repositórios para lidar com as operações do banco de dados e as classes de solicitação e resposta necessárias.

---

**Commit** 6ac28958a25f110a54442557630fad78a0efaaca:

Este commit adiciona a funcionalidade de criação de um novo abrigo no sistema, através das classes `ShelterEntityService` e `ShelterEntityServiceImpl`. Essa implementação também inclui testes para essa funcionalidade na classe `ShelterEntityServiceImplTest`.

**Arquivos Adicionados:** `ShelterEntityService.java`, `ShelterEntityServiceImpl.java`, `ShelterEntityServiceImplTest.java`

**Alterações:**

- `ShelterEntityService.java`: Interface de serviço criada para definir a operação de criação de abrigos.
- `ShelterEntityServiceImpl.java`: Implementa a interface `ShelterEntityService` e inclui a lógica para criar um abrigo no sistema. Ele busca um usuário responsável, cria um endereço e, finalmente, cria um abrigo. O abrigo criado é persistido no repositório de abrigos.
- `ShelterEntityServiceImplTest.java`: Testes para a funcionalidade de criação de abrigos. Ele verifica se a criação de um abrigo está funcionando como esperado e faz asserções para verificar a integridade dos dados do abrigo criado.

**Nota:** Este commit enfoca a implementação da lógica necessária para criar um abrigo e persisti-lo no banco de dados. Com isso, os usuários agora podem criar novos abrigos no sistema.

---




