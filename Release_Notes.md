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

**Commit** 6dc20873197bc7684422c477a9cbf4aeabfb8039:

Esse commit substitui a entidade User pela entidade UserContract nos mappers. Agora, as classes `UserMapper` e `UserEntityMapper` operam com `UserContract` em vez de `User`. Alterações correspondentes também foram refletidas nos respectivos testes para garantir que eles funcionem com a nova configuração.

**Arquivos Alterados:** `UserEntityMapper.java`, `UserMapper.java`, `UserEntityMapperTest.java`, `UserMapperTest.java`

**Alterações:**

- `UserEntityMapper.java`: A entidade 'User' foi substituída por 'UserContract' nos métodos do mapper.
- `UserMapper.java`: A entidade 'User' foi substituída por 'UserContract' nos métodos do mapper.
- `UserEntityMapperTest.java`: Testes foram atualizados para refletir as alterações no 'UserEntityMapper'.
- `UserMapperTest.java`: Testes foram atualizados para refletir as alterações no 'UserMapper'.

**Nota:** A principal mudança desse commit é a substituição da entidade `User` pela `UserContract` nos mappers. Isso proporciona maior flexibilidade e abstração na transformação de dados entre as camadas da aplicação. 

---

**Commit** 28c1baa345cb68cb7aa17b1cfbcb528481bdb0c4:

Este commit atualiza o uso da classe `Shelter` para a classe `ShelterContract` para manter a consistência em todo o código.

**Arquivos Alterados:** `ShelterEntityMapper.java`, `ShelterMapper.java`

**Alterações:**

- `ShelterEntityMapper.java`: `Shelter` foi substituído por `ShelterContract` nos métodos do mapper. Isso inclui mudanças nos comentários do método para refletir essa mudança.
- `ShelterMapper.java`: `Shelter` foi substituído por `ShelterContract` nos métodos do mapper. Isso inclui mudanças nos comentários do método, o método `mappedDonationsToShelter` para refletir essa mudança.

**Nota:** Este commit refatora o código para usar a interface `ShelterContract` ao invés da classe `Shelter` a fim de manter a consistência ao longo do código. Isso facilita a leitura e a manutenção do código, tornando-o mais eficiente.

---

**Commit** d0804574b881ba87391feee765a1fd94c7db51af:

Este commit aprimora a funcionalidade de mapeamento de objetos em `BuilderMapper`, tornando a função mais genérica e mais segura ao lançar exceções quando os parâmetros fornecidos são nulos.

**Arquivos Alterados:** `ExceptionDetails.java`, `BuilderMapper.java`, `UserEntityServiceImpl.java`, `UserEntityMapperTest.java`

**Alterações:**

- `ExceptionDetails.java`: Altera a mensagem de erro para `CLASS_MAPPING_FAILURE`.
- `BuilderMapper.java`: Atualiza a função `mapTo` para ser mais genérica e lançar exceções quando o objeto de origem ou a estratégia de mapeamento são nulos.
- `UserEntityServiceImpl.java`: Atualiza a chamada para a função `mapTo` em `searchUserByEmail`.
- `UserEntityMapperTest.java`: Atualiza os testes para refletir as alterações no `BuilderMapper`.

**Nota:** Este commit aprimora a funcionalidade de mapeamento de objetos em `BuilderMapper`, tornando o método mais robusto e seguro ao lidar com possíveis entradas nulas. Isso melhora a qualidade do código e o torna mais resistente a possíveis erros de execução.

---

**Commit** aa23f62db86eaf66eedb6a40a2284798776c360c:

Este commit inclui uma refatoração extensa no serviço `ShelterEntity` e testes relacionados. A lógica complexa foi reestruturada em métodos mais gerenciáveis e foram adicionados vários casos de teste para aumentar a cobertura e garantir a robustez do código.

**Arquivos Alterados:** `ShelterRepository.java`, `ShelterEntityServiceImpl.java`, `ShelterEntityMapperTest.java`, `ShelterEntityServiceImplTest.java`

**Alterações:**

- `ShelterRepository.java`: Atualiza o método `persist` para utilizar a classe `BuilderMapper` para mapeamento de entidades.
- `ShelterEntityServiceImpl.java`: Faz várias mudanças para melhoria da qualidade do código. A lógica de criação do abrigo e endereço e persistência dessas entidades foram movidas para métodos separados para melhor legibilidade. As mensagens de erro também foram melhoradas.
- `ShelterEntityMapperTest.java`, `ShelterEntityServiceImplTest.java`: Os testes foram atualizados para refletir as alterações no serviço `ShelterEntity`.

**Nota:** Este commit realiza uma refatoração extensa no serviço `ShelterEntity` e seus testes correspondentes para melhorar a qualidade do código e a cobertura dos testes. Isso aumenta a robustez do código e a facilidade de manutenção.

---

**Commit** a5e09f8a1fc54957d9dee7602381a7cd3254893a:

Esse commit adiciona documentação Javadoc detalhada para todas as classes de serviço e repositório, fornecendo descrições abrangentes de suas funções, métodos, autores e versões. A documentação é crucial para entender e manter corretamente o código no futuro.

**Arquivos Alterados:** 

Classe de Repositório:

- `AddressRepository.java`
- `DonationRepository.java`
- `UserRepository.java`

Classe de Serviço:
 
- `ShelterEntityService.java`
- `UserEntityService.java`
- `ShelterEntityServiceImpl.java` 

**Alterações:**

Nesse commit, cada classe teve alterações substancias em seus comentários, incrementando a documentação dos métodos, classes e suas funcionalidades de maneira detalhada. Como exemplo, temos a documentação dos métodos da classe `ShelterEntityService`, que descreve o propósito, parâmetros, retornos e possíveis exceções de cada método.

**Nota:** Este commit aumenta significativamente a legibilidade e a compreensibilidade do código por meio de comentários Javadoc detalhados. Isso é essencial para o entendimento correto do funcionamento do código e facilita a manutenção e futuras atualizações.

---

**Commit** 9372e7a263787d278e9792c2ef06682a62e83fa1:

Este commit atualiza o README.md para incluir um link direto para a documentação Swagger do projeto e modifica algumas tags em OpenApiConfig.java para refletir melhor as funcionalidades do sistema. Além disso, algumas importações não utilizadas foram removidas do arquivo ShelterEntityServiceImplTest.java para limpar o código.

**Arquivos Alterados:** `README.md`, `OpenApiConfig.java`, `ShelterEntityServiceImplTest.java`

**Alterações:**

- No `README.md`, foi adicionada a seção Swagger, que contém um link direto para a documentação Swagger do projeto.
- As tags em `OpenApiConfig.java` foram modificado de "Doadores" e "Receptores" para "Usuários" e "Abrigos" para refletir melhor as funcionalidades do sistema.
- No `ShelterEntityServiceImplTest.java`, foram removidas as importações não utilizadas `ShelterEntityMapper` e `lombok.SneakyThrows`.

**Nota:** A principal ênfase deste commit é atualizar a documentação e limpar o código para melhor manutenção e legibilidade. Ele visa aperfeiçoar a documentação do Swagger e refletir melhor as funcionalidades do sistema nas tags da API. Além disso, também se esforça para manter o código limpo, removendo importações não utilizadas.

---

**Commit** fca44bf9cfc282adf59ead2686be64dd1eb36ae1:

Este commit adiciona um manipulador global de exceções para gerenciar de maneira eficaz as respostas de erro durante o processamento das solicitações. Foi criada uma classe ExceptionDTO para armazenar e transferir detalhes das exceções lançadas.

**Arquivos Adicionados:** `ControllerExceptionHandler.java`, `ExceptionDTO.java`

**Alterações:**

- Implementada a classe `ControllerExceptionHandler.java`, que é um manipulador global de exceções para controladores. Esta classe tem vários métodos anotados com `@ExceptionHandler` para tratar diferentes tipos de exceções que podem ocorrer durante o processamento das solicitações e gera respostas de erro apropriadas.
- Criada a classe `ExceptionDTO` para representar um objeto de transferência de dados para transportar informações de exceção. Ela contém a mensagem e o código de status da exceção.

**Nota:** A principal ênfase deste commit é melhorar a manipulação de erros, o que contribui para a robustez do sistema. Foi usada a estratégia de manipulação de exceções global para lidar com exceções lançadas por qualquer classe de controlador e fornecer detalhes apropriados do erro na resposta.

---

**Commit** 723e0e1247c062a54a7f2a4ab62fe30a94f54c1b:

Este commit implementa os controladores `ShelterController` e `ShelterControllerImpl` para gerenciar as operações de abrigos. `ShelterController` define a interface de operações, enquanto `ShelterControllerImpl` é a implementação concreta onde os serviços são chamados. Além disso, os testes correspondentes também foram adicionados para `ShelterControllerImpl`.

**Arquivos Adicionados:** `ShelterController.java`, `ShelterControllerImpl.java`, `ShelterControllerImplTest.java`

**Alterações:**

- O arquivo `ShelterController.java` foi adicionado. Este arquivo é uma interface que define as operações de gerenciamento de abrigos.
- O arquivo `ShelterControllerImpl.java` foi adicionado. Este arquivo é a implementação concreta de `ShelterController` onde os serviços são chamados.
- O arquivo `ShelterControllerImplTest.java` foi adicionado. Este arquivo contém os testes para a implementação do controlador.

**Nota:** A principal ênfase deste commit é adicionar a criação dos abrigos e os testes correspondentes para a implementação.

---

**Commit** ff973370661a9720fa06f95f77e0edcea5243770

Este commit adicionou arquivos com a documentação do projeto. 

**Arquivos Adicionados:** varios `html`

**Alterações:**

- Foram adicionados arquivos `html` na diretório `docs/javaDoc`. Este arquivo é a documentação gerada pelo Javadoc para a API `ConectarDoacoesApplication`.

**Nota:** A principal ênfase deste commit é adicionar a documentação Javadoc referente a API `ConectarDoacoesApplication`, proporcionando um maior entendimento sobre as classes e seus métodos. 

---

**Commit**: a1a9a415349e9350779d91911ccdcee73a811152:

Neste commit, a mensagem de erro para quando um usuário não é encontrado foi modificada nos arquivos `ShelterEntityServiceImpl.java` e `ShelterEntityServiceImplTest.java`. Anteriormente, a mensagem era apenas "Usuário não encontrado". Agora, a mensagem foi alterada para indicar que o email do usuário responsável não foi encontrado para fornecer mais clareza.

**Arquivos Alterados:** `ShelterEntityServiceImpl.java`, `ShelterEntityServiceImplTest.java`

**Alterações:**

- No arquivo `ShelterEntityServiceImpl.java`, a constante `USER_NOT_FOUND` foi substituída por `USER_RESPONSIBLE_EMAIL_NOT_FOUND_ERROR`. A nova constante possui a mensagem alterada "Ops! Não conseguimos encontrar o e-mail do usuário responsável. Por gentileza, tente novamente.".
- A nova mensagem de erro é utilizada ao lançar a exceção `ShelterEntityFailuresException` na situação em que o usuário responsável não é encontrado.
- Nos dois testes do arquivo `ShelterEntityServiceImplTest.java`, também foi alterada a mensagem de erro esperada.


**Nota:** A principal ênfase deste commit é aprimorar a clareza das mensagens de erro, indicando com precisão a falha encontrada ao buscar o usuário responsável por email.

---

**Commit**: e4b26cf1c4f4f3aebabbb2f1b9ae5df670085bac:

Este commit adiciona documentação à classe `UserEntity`. A documentação fornece uma explicação detalhada do propósito desta classe, as anotações usadas e os atributos da classe. Isso inclui informações sobre a representação dos usuários no sistema, os detalhes das anotações do JPA e Lombok, e uma descrição dos atributos do usuário.

**Arquivo Alterado:** `UserEntity.java`

**Alterações:**

- A documentação foi adicionada para explicar o propósito da classe `UserEntity`.
- Detalhes das anotações usadas na classe são explicados – `@Entity`, `@Table`, `@AllArgsConstructor`, `@NoArgsConstructor`, `@Builder`, `@Getter`, `@Setter`.
- Os atributos da classe são descritos – `userId`, `userName`, `email`, `userProfile`, `userPassword`.

**Nota:** A principal ênfase desta atualização foi a adição de documentação à classe `UserEntity`, aumentando assim a compreensão do código e facilitando a manutenção.

---

**Commit** e6a6e737e3b4e80a98df6555bdebbd1d0341aa99:

Este commit otimiza o método `mapEntityList` no `ShelterRepository`. Agora, retorna-se uma lista vazia imutável das Java Collections em vez de instanciar um novo ArrayList. Além disso, a criação de uma lista intermediária durante o mapeamento de entidades foi eliminada.

**Arquivo Alterado:** `ShelterRepository.java`

**Alterações:**

- Foi substituída a instância de um novo ArrayList por uma lista vazia imutável das Java Collections quando as entidades são nulas ou vazias em `mapEntityList`.
- A criação de uma lista intermediária durante o mapeamento de entidades foi eliminada. Agora, realiza-se diretamente a stream das entidades para a lista de retorno.

**Nota:** A ênfase desta commit é na melhoria da eficiência do método `mapEntityList` no `ShelterRepository`, otimizando a construção da lista de retorno.

---

**Commit** 1c6d58ba5c1711a905c1d944845a4169c8e3afb3:

Este commit adiciona melhorias ao repositório de usuário `UserRepository`, adicionando validações e expandindo as funcionalidades de busca, persistência e deleção de usuários. Além disso, testes unitários correspondentes foram adicionados a `UserRepositoryTest.java` para garantir o funcionamento adequado dos novos métodos.

**Arquivos Alterados:** `UserRepository.java`, `UserRepositoryTest.java`

**Alterações:**

- Novos métodos no `UserRepository.java` foram implementados para melhorar a funcionalidade de busca, persistência e deleção de usuários.
- Validadores foram adicionados para garantir a integridade dos dados no `UserRepository.java`.
- Novos testes unitários foram adicionados a `UserRepositoryTest.java` para garantir que os novos métodos estejam funcionando corretamente. 

**Nota:** A ênfase deste commit é expandir a funcionalidade do repositório de usuário, garantindo a integridade dos dados através da implementação de validações. 

---

**Commit** b0b4a3b592edc64541f0d7e7a2ed5820becf058d:

Este commit adiciona uma nova funcionalidade de criação de usuários ao sistema. Novas classes de Request e Response foram adicionadas, novos métodos de serviço foram implementados para a criação de usuários e os testes correspondentes foram criados. Além disso, estratégias para tratar erros durante a criação de usuários, como tentativas de registrar um endereço de e-mail já existente, foram adicionadas.

**Arquivos Alterados:** `UserEntityCreationRequest.java`, `UserEntityCreatedResponse.java`, `UserEntityService.java`, `UserEntityServiceImpl.java`

**Alterações:**

- Implementação da classe `UserEntityCreationRequest` que coleta os dados fornecidos pelo usuário durante a solicitação de criação de um perfil.
- Implementação da classe `UserEntityCreatedResponse` que é retornada após a criação de uma nova entidade de usuário, contendo informações pertinentes sobre o usuário.
- Novos métodos foram adicionados na interface `UserEntityService` e implementados em `UserEntityServiceImpl`, para a criação do usuário.
- Tratamento de erros foi adicionado em `UserEntityServiceImpl` para lidar com cenários onde o endereço de e-mail fornecido pelo usuário já existe no banco de dados.

**Nota:** Esta commit adiciona funcionalidades significativas ao sistema, possibilitando a criação de novos usuários. Testes correspondentes para essas funcionalidades também foram adicionados.

---

**Commit** 669f1809eb4e13ba7113c2362a458c5a6b26f51f:

Este commit adicionou a interface `UserController` e sua implementação na classe `UserControllerImpl`. O foco principal dos novos arquivos é lidar com operações relacionadas aos usuários, principalmente na criação de novos usuários, configurando as respectivas requisições e respostas para esses endpoints.

**Arquivos Criados:** `UserController.java`, `UserControllerImpl.java`

**Alterações:**

- Adicionada a Interface `UserController`, que define o método para criação de um novo usuário.
- Implementação da classe `UserControllerImpl`, que implementa o método definido pela interface `UserController`. A classe se utiliza dos serviços definidos em `UserEntityService` para criar novos usuários e retorna uma resposta formatada de acordo com a classe `UserEntityCreatedResponse`.
- Documentação completa, incluindo as anotações para a documentação da API.

**Nota:** A ênfase deste commit é adicionar a funcionalidade de criação de usuário, gerenciando as requisições HTTP e as respostas correspondentes, facilitando a interação de clientes com a criação de novos usuários.

---


---

## **_Release 1.1.0_**

**Commit** 0ce258ee8e9583aa5415cdb39ce45f53416eb354

Este commit remove inicializações desnecessárias de objetos em várias classes de mapeamento. Configurações de exclusão de cobertura de código foram incluídas e propriedades para Sonar e JaCoCo foram adicionadas ao `pom.xml` para melhor gerenciamento e análise de qualidade de código.

**Arquivos alterados:** `.gitignore`, `pom.xml`, `UserEntityFailuresException.java`, `BuilderMapper.java`, `DonationMapper.java`, `ShelterEntityMapper.java`, `ShelterMapper.java`, `UserEntityMapper.java`, `UserMapper.java`, `AddressRepository.java`, `DonationRepository.java`.

**Alterações:**

- Adicionado `qodana.sarif.json` ao arquivo `.gitignore`.
- Configurações para Sonar e JaCoCo foram adicionadas ao `pom.xml`, junto com as configurações de exclusão de cobertura de código.
- Removida inicialização desnecessária de objetos nas classes `BuilderMapper`, `DonationMapper`, `ShelterEntityMapper`, `ShelterMapper`, `UserEntityMapper` e `UserMapper`.
- Corrigida formatação no Javadoc das classes `AddressRepository` e `DonationRepository`.

**Nota:** Essa commit é focada em refatorar o mapeamento de objetos e melhorar a configuração do JaCoCo e Sonar para análise de qualidade de código.

---

**Commit** 09b07b152878ed06ce99a36fe1a93fc2c82ff070:

Este commit adiciona a ferramenta de análise de código Qodana ao projeto. Adicionamos o arquivo de configuração `qodana.yaml`, que define várias inspeções a serem realizadas e o arquivo `.github/workflows/qodana_code_quality.yml` para executar a verificação de qualidade de código usando GitHub Actions.

**Arquivos Alterados:** `.github/workflows/qodana_code_quality.yml`, `qodana.yaml`

**Alterações:**

- No arquivo `.github/workflows/qodana_code_quality.yml`, foram definidas as ações de verificação de qualidade de código a serem executadas quando um pull request é criado ou quando qualquer push é feito para os branches `main`, `develop`, e `release/*`.
- O arquivo `qodana.yaml` foi adicionado. Nele, foi definida a inspeção do perfil `qodana.starter` para a análise do código, e foram ativas as inspeções `JvmCoverageInspection`, `UNUSED_IMPORT` e `JavadocDeclaration`.

**Nota:** A principal ênfase desta confirmação é adicionar a análise de código Qodana ao projeto para melhorar a qualidade do código.

---

**Commit** c4595afab45cff5afe3bf2242d9d0956ed87847a:

Este commit efetua uma refatoração em diversos testes, aprimorando a maneira como algumas exceções são verificadas. Foi adicionado o método ParameterizedTest para tornar os testes mais dinâmicos e menos repetitivos. Além disso, foram removidas várias importações e códigos que não estavam sendo utilizados, visando manter o código limpo e de fácil manutenção. Outra mudança significativa foi a atualização no método "validateNotNullOrEmpty" na classe "ValidationUtils", no qual foi utilizado o recurso instanceof com padrão de variável do Java 17+.

**Arquivos Alterados:** `ShelterEntityMapper.java`, `ShelterEntityServiceImpl.java`, `ValidationUtils.java`, `ShelterControllerImplTest.java`, `UserMapperTest.java`, `ShelterEntityServiceImplTest.java`, `UserTest.java`

**Alterações:**

- Em `ShelterEntityMapper.java`, a instância de `User` foi removida do cast de `source.getUser()`, tornando a chamada direta.
- Em `ShelterEntityServiceImpl.java`, foi removida a dependência do `DonationRepository`, pois não estava sendo utilizada.
- Em `ValidationUtils.java`, o recurso instanceof com padrão de variável do Java 17+ foi implementado no método `validateNotNullOrEmpty`.
- Em `ShelterControllerImplTest.java`, foram removidas diversas importações que não estavam sendo utilizadas.
- Em `UserMapperTest.java`, implementou-se o método ParameterizedTest para otimizar os testes relacionados à `UserEntity`.
- Em `ShelterEntityServiceImplTest.java`, a entidade `Donation` foi removida, pois não estava sendo utilizada.
- Em `UserTest.java`, foi removido o setUp que não estava sendo utilizado.

**Nota:** A principal ênfase deste commit é melhorar a qualidade dos testes, torná-los menos repetitivos e manter o código limpo e de fácil manutenção.

---

**Commit** 3c5cfeb9842012e6320f3cc8ca6f4c5c31646f0b:

Este commit remove algumas importações desnecessárias nos arquivos de teste `UserMapperTest.java` e `ShelterEntityServiceImplTest.java`, visando tornar o código mais limpo e eficiente. As importações removidas incluem `UuidUtilsException` e `Donation`, as quais não estavam sendo utilizadas.

**Arquivos Alterados:** `UserMapperTest.java`, `ShelterEntityServiceImplTest.java`

**Alterações:**

- Em `UserMapperTest.java`: 
   - Foi removida a importação do `UuidUtilsException` e `UuidUtils`, pois não estavam sendo usados nesses testes. 

- Em `ShelterEntityServiceImplTest.java`: 
   - Foi removida a importação da entidade `Donation`, pois a mesma não estava sendo utilizada no teste.

**Nota:** O principal foco deste commit é melhorar a limpeza do código removendo importações desnecessárias nos testes.

---
## **_Release 1.1.0_**

**Commit** 0ce258ee8e9583aa5415cdb39ce45f53416eb354

Este commit remove inicializações desnecessárias de objetos em várias classes de mapeamento. Configurações de exclusão de cobertura de código foram incluídas e propriedades para Sonar e JaCoCo foram adicionadas ao `pom.xml` para melhor gerenciamento e análise de qualidade de código.

**Arquivos alterados:** `.gitignore`, `pom.xml`, `UserEntityFailuresException.java`, `BuilderMapper.java`, `DonationMapper.java`, `ShelterEntityMapper.java`, `ShelterMapper.java`, `UserEntityMapper.java`, `UserMapper.java`, `AddressRepository.java`, `DonationRepository.java`.

**Alterações:**

- Adicionado `qodana.sarif.json` ao arquivo `.gitignore`.
- Configurações para Sonar e JaCoCo foram adicionadas ao `pom.xml`, junto com as configurações de exclusão de cobertura de código.
- Removida inicialização desnecessária de objetos nas classes `BuilderMapper`, `DonationMapper`, `ShelterEntityMapper`, `ShelterMapper`, `UserEntityMapper` e `UserMapper`.
- Corrigida formatação no Javadoc das classes `AddressRepository` e `DonationRepository`.

**Nota:** Essa commit é focada em refatorar o mapeamento de objetos e melhorar a configuração do JaCoCo e Sonar para análise de qualidade de código.

---

**Commit** 09b07b152878ed06ce99a36fe1a93fc2c82ff070:

Este commit adiciona a ferramenta de análise de código Qodana ao projeto. Adicionamos o arquivo de configuração `qodana.yaml`, que define várias inspeções a serem realizadas e o arquivo `.github/workflows/qodana_code_quality.yml` para executar a verificação de qualidade de código usando GitHub Actions.

**Arquivos Alterados:** `.github/workflows/qodana_code_quality.yml`, `qodana.yaml`

**Alterações:**

- No arquivo `.github/workflows/qodana_code_quality.yml`, foram definidas as ações de verificação de qualidade de código a serem executadas quando um pull request é criado ou quando qualquer push é feito para os branches `main`, `develop`, e `release/*`.
- O arquivo `qodana.yaml` foi adicionado. Nele, foi definida a inspeção do perfil `qodana.starter` para a análise do código, e foram ativas as inspeções `JvmCoverageInspection`, `UNUSED_IMPORT` e `JavadocDeclaration`.

**Nota:** A principal ênfase desta confirmação é adicionar a análise de código Qodana ao projeto para melhorar a qualidade do código.

---

**Commit** c4595afab45cff5afe3bf2242d9d0956ed87847a:

Este commit efetua uma refatoração em diversos testes, aprimorando a maneira como algumas exceções são verificadas. Foi adicionado o método ParameterizedTest para tornar os testes mais dinâmicos e menos repetitivos. Além disso, foram removidas várias importações e códigos que não estavam sendo utilizados, visando manter o código limpo e de fácil manutenção. Outra mudança significativa foi a atualização no método "validateNotNullOrEmpty" na classe "ValidationUtils", no qual foi utilizado o recurso instanceof com padrão de variável do Java 17+.

**Arquivos Alterados:** `ShelterEntityMapper.java`, `ShelterEntityServiceImpl.java`, `ValidationUtils.java`, `ShelterControllerImplTest.java`, `UserMapperTest.java`, `ShelterEntityServiceImplTest.java`, `UserTest.java`

**Alterações:**

- Em `ShelterEntityMapper.java`, a instância de `User` foi removida do cast de `source.getUser()`, tornando a chamada direta.
- Em `ShelterEntityServiceImpl.java`, foi removida a dependência do `DonationRepository`, pois não estava sendo utilizada.
- Em `ValidationUtils.java`, o recurso instanceof com padrão de variável do Java 17+ foi implementado no método `validateNotNullOrEmpty`.
- Em `ShelterControllerImplTest.java`, foram removidas diversas importações que não estavam sendo utilizadas.
- Em `UserMapperTest.java`, implementou-se o método ParameterizedTest para otimizar os testes relacionados à `UserEntity`.
- Em `ShelterEntityServiceImplTest.java`, a entidade `Donation` foi removida, pois não estava sendo utilizada.
- Em `UserTest.java`, foi removido o setUp que não estava sendo utilizado.

**Nota:** A principal ênfase deste commit é melhorar a qualidade dos testes, torná-los menos repetitivos e manter o código limpo e de fácil manutenção.

---

**Commit** 3c5cfeb9842012e6320f3cc8ca6f4c5c31646f0b:

Este commit remove algumas importações desnecessárias nos arquivos de teste `UserMapperTest.java` e `ShelterEntityServiceImplTest.java`, visando tornar o código mais limpo e eficiente. As importações removidas incluem `UuidUtilsException` e `Donation`, as quais não estavam sendo utilizadas.

**Arquivos Alterados:** `UserMapperTest.java`, `ShelterEntityServiceImplTest.java`

**Alterações:**

- Em `UserMapperTest.java`: 
   - Foi removida a importação do `UuidUtilsException` e `UuidUtils`, pois não estavam sendo usados nesses testes. 

- Em `ShelterEntityServiceImplTest.java`: 
   - Foi removida a importação da entidade `Donation`, pois a mesma não estava sendo utilizada no teste.

**Nota:** O principal foco deste commit é melhorar a limpeza do código removendo importações desnecessárias nos testes.

---

**Commit** b1a01823449146c4a727faec3f6341698e867109:

Este commit adiciona uma validação ao método `createShelter` para garantir que uma solicitação de criação de abrigo não seja nula. Também foi incluído um novo teste para verificar a exceção lançada quando uma solicitação nula é passada. Essas mudanças visam evitar erros causados por solicitações de criação nulas e fornecer mensagens de erro mais claras para o usuário.

**Arquivos Alterados:** `ShelterEntityServiceImpl.java`, `ShelterEntityServiceImplTest.java`

**Alterações:**

- Adicionada a validação `ValidationUtils.validateNotNullOrEmpty` no método `createShelter` de `ShelterEntityServiceImpl` para verificar se o pedido de criação de um abrigo não é nulo.
- Incluído o campo `REQUEST_VALIDATION_ERROR_MESSAGE` em `ShelterEntityServiceImpl`.
- Adicionado o teste `shouldThrowShelterEntityFailuresExceptionWhenCreatingShelterWithNullArgument` em `ShelterEntityServiceImplTest` para verificar a exceção lançada quando uma solicitação nula é passada para `createShelter`.

**Nota:** A principal ênfase desta confirmação é adicionar uma validação de solicitação nula no método `createShelter` e fornecer mensagens de erro claras quando uma solicitação nula é passada.

---

**Commit** 68b85312f52c9a999c6279671af8d6c388b6018a:

Este commit atualiza o serviço de criação de abrigos para usar `AddressEntityService`. O código foi refatorado para utilizar a classe AddressEntityService no processo de criação de abrigos em vez de diretamente tratar os dados do endereço. Isso simplifica a classe ShelterEntityServiceImpl, e melhora a separação de responsabilidades, pois agora a lógica de criação de endereços está inteiramente dentro de AddressEntityService.

**Arquivos Alterados:** `ShelterEntityServiceImpl.java`

**Alterações:**

- As referências à `AddressRepository` foram removidas de `ShelterEntityServiceImpl.java` e substituídas por `AddressEntityService`.
- O método `createAndReturnShelterInstance` de `ShelterEntityServiceImpl.java` foi alterado para usar `AddressEntityService.createAndSaveAddressFromDto(request.getAddress())` ao invés de criar e salvar o endereço diretamente.
- A lógica de criação e salvamento de endereços que estava em `ShelterEntityServiceImpl.java` foi movida para `AddressEntityService`.
- Os testes unitários em `ShelterEntityServiceImplTest.java` foram ajustados para refletir as mudanças feitas.

**Nota:** A principal ênfase desta confirmação é melhorar a separação de responsabilidades no serviço de criação de abrigos, movendo a lógica de criação de endereços para o próprio `AddressEntityService`.

---

**Commit** b25bb9dc76c73cc0e2b5315994c9170a23ff2703:

Este commit introduz um novo serviço chamado `AddressEntityServiceImpl`, responsável pela criação e gravação de entidades de endereço. Esse serviço aceita DTOs, valida, transforma-os em entidades de endereço e, em seguida, persiste-os no banco de dados. Em caso de falha, um tratamento de exceções foi implementado. Além disso, a interface `AddressEntityService` também foi criada, esta define o contrato para o serviço de endereço.

**Arquivos Alterados:** `AddressEntityService.java`, `AddressEntityServiceImpl.java`

**Alterações:**

- A interface `AddressEntityService` foi criada. Ela define o contrato para o serviço que lida com a entidade de endereço. A interface define um método chamado `createAndSaveAddressFromDto`, que é responsável por criar e salvar uma entidade de endereço com base em um DTO.

- A classe de serviço `AddressEntityServiceImpl` foi criada. Esta classe implementa a `AddressEntityService` e fornece a implementação para `createAndSaveAddressFromDto`. A implementação valida o DTO de entrada, cria a entidade de endereço e a persiste no banco de dados.

- Durante a criação da entidade de endereço, `AddressEntityServiceImpl` faz uso da `AddressFactory`. Se ocorrer algum erro durante a criação da entidade, `AddressCreationFailureException` será lançada.

- Para salvar a entidade no repositório, `AddressEntityServiceImpl` primeiro mapeia o objeto de endereço para a entidade relevante usando `BuilderMapper` e, em seguida, salva a entidade no repositório. Se ocorrer um erro durante o mapeamento, uma `AddressEntityFailuresException` será lançada.

- Em caso geral, se houver uma falha ao criar ou salvar a entidade de endereço, `AddressEntityFailuresException` será lançada.

**Nota:** A ênfase principal deste commit está na criação de um novo serviço dedicado ao gerenciamento de entidades de endereço. O serviço permite converter DTOs de endereço em entidades de endereço e persisti-las no banco de dados. O serviço também adiciona com robustez ao implementar tratamento de exceção adequado para situações onde a conversão ou persistência falha.

---

**Commit** f68969e62ba7b5d8b8d7f564e98792c48e2926fe:

Este commit adiciona uma série de testes para a implementação de `AddressEntityServiceImpl`. Os testes cobrem o comportamento esperado da implementação bem-sucedida, bem como tratamentos de erro quando há entradas inválidas ou nulas.

**Arquivo Alterado:** `AddressEntityServiceImplTest.java`

**Alterações:**

- Nova classe de teste `AddressEntityServiceImplTest` adicionada, com vários casos de teste.
- Os casos de teste verificam o comportamento esperado ao manipular o objeto `AddressDTO`, como a exceção apropriada sendo lançada quando o DTO está nulo ou quando campos individuais no DTO são nulos ou em branco.
- Uso extensivo de `MockedStatic`, `ArgumentCaptor` e `assertThrows` para verificar o comportamento do serviço `AddressEntityServiceImpl`.
  

**Nota:** Este commit se concentra na verificação dos casos de tratamento de erro, garantindo que a implementação `AddressEntityServiceImpl` se comporta como esperado quando encontrados dados inválidos ou nulos.

---

**Commit** f0dfe4278bfcbd52c1bce7a45f8d5c6b42ccfda2:

Este commit aumenta o arquivo README.md para incluir informações mais específicas relacionadas ao projeto 'Conectar Doações'. Ele introduz um novo emblema CI Prod no título e o complementa com uma seção de conteúdo abrangente que descreve os títulos dos componentes do projeto, como 'Recursos', 'Arquitetura Hexagonal', 'Swagger', 'Docker' e 'UML'. A comparação também inclui uma descrição detalhada de cada uma dessas seções, incluindo códigos, diagramas e explicações.

**Arquivos Alterados:** `README.md`

**Alterações:**

- Introduzido um novo emblema "CI Prod" na parte superior do arquivo README.md.
- Incluía uma nova seção "Conteúdo" que detalha várias seções importantes do arquivo README.md.
- Foram adicionadas as descrições detalhadas das seções 'Recursos', 'Arquitetura Hexagonal', 'Swagger', 'Docker' e 'UML'. 
- Foi incluída a explicação de como executar e parar o projeto com o Docker.
- Foi proporcionada uma descrição visual da arquitetura do projeto por meio de um diagrama UML.

**Nota:** A principal finalidade deste commit é melhorar a compreensão do projeto 'Conectar Doações' ao fornecer informações estendidas no arquivo README.md.

---

**Commit** af98b49c6d2254a2b9477e67cab6279b7b25505f:

Este commit adicionou a validação de perfil e a restrição de vinculação de múltiplos abrigos para um usuário responsável. Assegurando que o usuário designado como responsável por um abrigo tenha o perfil de "Beneficiary" (e não "Donor"), e que esse usuário não seja já responsável por outro abrigo. Os testes correspondentes também foram adicionados.

**Arquivos Alterados:** `ShelterRepository.java`, `ShelterEntityServiceImpl.java`, `ShelterRepositoryIntegrationTest.java`, `ShelterEntityServiceImplTest.java`

**Alterações:**

- Foram adicionados dois novos métodos na classe `ShelterEntityServiceImpl.java` para validar se o usuário responsável possui perfil de beneficiário e se já está vinculado a algum outro abrigo.
- A inclusão do método `findShelterEntitiesByResponsibleUser_Email(String responsibleUserEmail)` na interface `ShelterRepository`. Esse método busca por abrigos associados a um determinado usuário.
- A implementação de testes, `ShelterRepositoryIntegrationTest.java` e `ShelterEntityServiceImplTest.java`, respectivamente para os itens acima mencionados.

**Nota:** O propósito principal desse commit é assegurar que o usuário responsável por um abrigo atenda aos requisitos necessários, otimizando a lógica no serviço `ShelterEntityService`.

---

**Commit** 015a7e5eda4e0c3ee5b716ecf044934c9c5acb1c:

Neste commit, uma seção de Javadoc foi adicionada ao arquivo README. A nova seção fornece um link para a documentação detalhada do Javadoc, permitindo que os usuários acessem mais facilmente essa documentação.

**Arquivo alterado:** `README.md`

**Alterações:**

- Adicionado um novo item ao índice do README.
- Inclusão de uma nova seção de Javadoc no README, com um link para a documentação gerada pelo Javadoc.

**Nota:** A essência deste commit é oferecer uma referência acessível à documentação do Javadoc através da inclusão deste link diretamente no arquivo README.

---

**Commit** caafb80de0e0f487d9a21a3a75535befe56f04cc:

Este commit adiciona registros de log em vários serviços e utilitários no pacote. Isso inclui a classe ValidationUtils, bem como os serviços de endereço, usuário e abrigo. Além disso, as mensagens de log foram adicionadas em vários pontos para indicar o sucesso ou falha de eventos específicos. Assim, melhorando a capacidade de rastreabilidade e debug das operações.

**Arquivos Alterados:** `AddressEntityServiceImpl.java`, `ShelterEntityServiceImpl.java`, `UserEntityServiceImpl.java`, `ValidationUtils.java`

**Alterações:**

- A anotação `@Slf4j` foi adicionada nas classes `AddressEntityServiceImpl.java`, `ShelterEntityServiceImpl.java`, `UserEntityServiceImpl.java` e `ValidationUtils.java` para habilitar o registro de log.
- Mensagens de log foram adicionadas em vários blocos `catch` para registrar situações de erro. Mensagens de sucesso também foram adicionadas em determinados pontos-chave, como após a criação de um novo endereço ou abrigo, para registrar a realização bem-sucedida dessas operações.

**Nota:** A principal ênfase deste commit é melhorar a rastreabilidade das operações por meio do registro de log. Isso facilitará a identificação e a resolução de problemas, aumentando assim a qualidade do código.

---

**Commit** 46f2bc3884920f138e12e31ef86519646394a480:

Este commit aprimora o rastreamento ao integrar logs de erro em várias classes mappers (`AddressMapper`, `ShelterEntityMapper`, `UserMapper`, etc). Isso aprimora a capacidade de rastrear os processos de mapeamento, facilitando a identificação de problemas e promovendo melhorias na depuração e manutenção do código.

**Arquivos Alterados:** `AddressMapper.java`, `DonationMapper.java`, `ShelterEntityMapper.java`, `ShelterMapper.java`, `UserEntityMapper.java`, `UserMapper.java`.

**Alterações:**

- Logs de erro foram adicionados em todas as classes mappers. Isso inclui a captura e log de erros ao mapear de entidades para objetos e vice-versa.

**Notas:**

Este commit foca em melhorar a capacidade de rastreamento de erros durante os processos de mapeamento, o que facilita a identificação de problemas e a depuração. Cada classe mapper agora gera um log quando ocorre um erro durante o mapeamento, fornecendo detalhes sobre o erro para auxiliar na resolução de problemas.

---

**Commit** 72b5c5fa03e4879ee0e1e9e29c6c1e582493aab8:

Este commit adiciona um manipulador de exceção para erros de leitura de mensagens HTTP no manipulador de exceções global (ControllerExceptionHandler). Esse tipo de erro ocorre quando há uma falha de sintaxe no corpo de uma solicitação HTTP. Agora, quando essa exceção é lançada, uma mensagem de erro é registrada no log e uma resposta BAD_REQUEST (400) é retornada ao cliente, indicando que a solicitação era inválida ou não pôde ser entendida pelo servidor.

**Arquivos Alterados:** `ControllerExceptionHandler.java`

**Alterações:**

- Adicionado um novo método para lidar com erros do tipo HttpMessageNotReadableException em `ControllerExceptionHandler`.
- Agora, a exceção registra uma mensagem de erro no log.
- Retorna uma resposta BAD_REQUEST (400) ao cliente quando ocorre essa exceção.

**Nota:** Este commit foca em melhorar a manipulação de erros relacionados a falhas de sintaxe no corpo de uma solicitação HTTP, fornecendo uma resposta apropriada para o cliente e registrando o erro para futuras análises.

---

**Commit** 96d2f9d710260dcf84af8bc421a2e34d740adb3b:

Este commit atualiza as condições para construção e envio nos workflows de CI. O processo de construção e envio agora é executado apenas quando um pull request é mesclado na branch "release/*" para o ci-hlg.yaml e na branch "main" para o ci-prod.yaml. Isto tem como objetivo otimizar as operações de CI e evitar execuções não necessárias.

**Arquivos Alterados:** `.github/workflows/ci-hlg.yaml`, `.github/workflows/ci-prod.yaml`

**Alterações:**

- Adicionada condição de execução em `ci-hlg.yaml` e `ci-prod.yaml`. A condição verifica se um pull request foi mesclado e se ocorreu nas branches corretas.

**Nota:** A principal ênfase desta confirmação é otimizar as operações de CI, garantindo que as tarefas de construção e envio sejam executadas apenas nas condições corretas, evitando possíveis execuções desnecessárias.

---

**Commit** 38ab5f3ee061d869f03e4042d7b4776d172abaa6:

Este commit atualiza o fluxo de trabalho para usar a ação docker/build-push-action no CI ao invés do comando `docker build` e `docker push` diretos. Isso simplifica o script além de fornecer uma maneira padronizada de realizar essas ações.

**Arquivos Alterados:** `.github/workflows/ci-hlg-docker.yaml`, `.github/workflows/ci-hlg.yaml`, `.github/workflows/ci-prod-docker.yaml`, `.github/workflows/ci-prod.yaml`, `.github/workflows/sonar-cloud.yaml`

**Alterações:**

- Atualizado o fluxo de trabalho CI (Continuous Integration) para usar `docker/build-push-action` em vez dos comandos diretos `docker build` e `docker push` nos arquivos `.github/workflows/ci-hlg-docker.yaml`, `.github/workflows/ci-hlg.yaml`, `.github/workflows/ci-prod-docker.yaml`, `.github/workflows/ci-prod.yaml`.
- O arquivo `.github/workflows/sonar-cloud.yaml` foi atualizado para remover a verificação de eventos push nos branches main, develop e release.

**Nota:** A principal ênfase deste commit é a simplificação e padronização do fluxo de trabalho CI para uso com o Docker.

---

**Commit** 9cf814fa8a16208bb5f2b4879eb53ba3ac6ee8ad:

Esse commit adiciona uma nova exceção, `DonationEntityFailuresException`, para tratar especificamente falhas relacionadas as entidades de doação. Além disso, a enumeração `ExceptionDetails` foi estendida para incluir uma nova mensagem de erro para falhas em operações de doação. Uma nova manipulação de exceção foi adicionada no `ControllerExceptionHandler` para capturar e responder apropriadamente a exceção lançada por essa operação.

**Arquivos Alterados:** `ControllerExceptionHandler.java`, `ExceptionDetails.java`, `DonationEntityFailuresException.java`

**Alterações:**

- Nova exceção `DonationEntityFailuresException` adicionada para lidar com falhas em operações relacionadas a entidades de doação.
- Aumento do enum `ExceptionDetails` para incluir uma nova mensagem de erro para falhas em operações de doação.
- Adicionada a manipulação de exceção em `ControllerExceptionHandler` para capturar e responder apropriadamente a exceção lançada.
- MServiço de manipulação de exceções adicionado ao arquivo `ControllerExceptionHandler.java` para tratar `DonationEntityFailuresException`.
- Nova mensagem de erro `DONATION_OPERATION_FAILURE` adicionada ao enum `ExceptionDetails`.

**Nota:** A principal ênfase deste commit é melhorar o tratamento de erro para operações de doação. Especificamente, adicionando uma nova exceção e uma nova mensagem de erro para falhas em operações de doação.

---

**Commit** c22c759ac75ce596fc8cbad149477d1bfc183608:

Este commit adiciona recursos para gerenciar doações no sistema, incluindo a conversão e persistência de doações. Esse recurso é importante para armazenar informação sobre as doações feitas pelos usuários. A funcionalidade foi implementada utilizando a estrutura padrão do sistema, com DTOs, serviços, e testes relacionados.

**Arquivos Alterados:** `DonationDTO.java`, `DonationEntityService.java`, `DonationEntityServiceImpl.java`

**Alterações:**

- Adicionado o DTO de doação (`DonationDTO.java`) que é utilizado para transportar dados entre processos.
- Criada a interface `DonationEntityService.java` que especifica métodos para realizar operações como conversão e persistência de doações em nossa base de dados.
- Implementada a classe `DonationEntityServiceImpl.java` que possui lógica de negócios para gerenciar doações, incluindo a conversão de DTOs para entidades de doação e a persistência dessas entidades no repositório.

**Nota:** A principal ênfase deste commit é adicionar recursos para gerenciar doações no sistema. Os arquivos novos são adicionados e implementados com o objetivo de tratar as doações efetuadas pelos usuários.

---

**Commit** 1e90b77a10d22ed148efc964ccc47cc9cc06e103:

Este commit ajusta a maneira como as doações são convertidas e salvas no sistema. Todo o processo agora é manipulado dentro do método 'convertAndSaveDonationDTO', removendo a necessidade de uso do 'DonationMapper'. O tipo de retorno do método também foi alterado de 'Donation' para 'DonationEntity'. Em consequência, ajustes foram necessários nas classes de teste.

**Arquivos Alterados:** `DonationEntityService.java`, `DonationEntityServiceImpl.java`, `DonationEntityServiceImplTest.java`

**Alterações:**

- O tipo de retorno do método 'convertAndSaveDonationDTO' da classe `DonationEntityService` foi alterado de 'Donation' para 'DonationEntity'.
- O método 'convertAndSaveDonationDTO' da classe `DonationEntityServiceImpl` foi atualizado para persistir diretamente a `DonationEntity` após a conversão do `DonationDTO`.
- O método de teste 'shouldConvertDonationDTOANDSaveDonationEntityANDValidateSavedDonation' da classe `DonationEntityServiceImplTest` foi atualizado para refletir a alteração do tipo de retorno do método 'convertAndSaveDonationDTO'.

**Nota:** A principal ênfase desta confirmação é melhorar a conversão e persistência de doações, removendo a necessidade de uso do 'DonationMapper' e direcionando a persistência para o próprio método de conversão.

---

**Commit** 3ae3e2930977667fd37a47e3a7cc956db405de7f:

Este commit adiciona novas classes de Request e Response para manipular a recepção de doações, bem como um novo Mapper para converter entidades Shelter para Responses. Essas classes fortalecem o encapsulamento das informações e melhoram a clareza na comunicação cliente-servidor.

**Arquivos Adicionados:** `ReceiveDonationResponseFromShelterEntityMapper.java`, `ReceiveDonationRequest.java`, `ReceiveDonationResponse.java`

**Adições:**

- Adicionada a classe `ReceiveDonationResponseFromShelterEntityMapper`, que é responsável pela conversão de objetos do tipo `ShelterEntity` para `ReceiveDonationResponse`.
- Adicionada a classe `ReceiveDonationRequest` para encapsular as informações presentes em uma solicitação de recebimento de doação.
- Adicionada a classe `ReceiveDonationResponse` que encapsula as informações de resposta quando um abrigo recebe uma doação.

**Nota:** A ênfase principal deste commit é melhorar o encapsulamento das informações e a clareza na comunicação cliente-servidor ao lidar com a recepção de doações.

---

**Commit** f76deea5e0a74153a89b015cfa5e96242d832ce3:

Este commit adiciona testes de mapeamento e validação da entidade de doação. Os testes foram incluídos no pacote src/test/java. Isso inclui testes para validar o mapeamento correto de entidades sem doações e com listas de doações vazias. Também o teste garante a correta conversão de uma doação DTO com quantidade zero para uma entidade, que é salva e validada como um.

**Arquivos Alterados:** `ReceiveDonationResponseFromShelterEntityMapperTest.java`, `DonationEntityServiceImplTest.java`

**Alterações:**

- A classe `ReceiveDonationResponseFromShelterEntityMapperTest` foi criada para implementar testes de mapeamento da entidade `ShelterEntity` e validação de respostas de recebimento de doação.
- Em `DonationEntityServiceImplTest.java`, o nome do método foi alterado de `shouldConvertDonationDTOANDSaveDonationEntityANDValidateSavedDonation` para `shouldConvertDonationDtoToEntitySaveAndValidate`, tornando-o mais expressivo.
- Adicionado novo teste `shouldConvertDonationDtoWithZeroAmountToEntitySaveAndValidateAsOne` para validar a lógica de quando a quantidade da doação é zero ou negativa, e o serviço deve ajustá-la para um no momento de salvamento.
  

**Nota:** Este commit é significativo para verificar a robustez da conversão, mapeamento, persistência e validação das entidades de doação.

---

**Commit** e616b50455e2fd737b93192aa1a3067a94c86371:

Este commit atualiza a imagem do MySQL usada no serviço do docker. A versão foi modificada para 'mysql:8.2.0-oraclelinux8' em vez da versão 'latest', garantindo maior controle sobre as versões do software.

**Arquivo Alterado:** `compose.yaml`

**Alterações:**

- Imagem do MySQL no serviço Docker alterada de "mysql:latest" para "mysql:8.2.0-oraclelinux8".

**Nota:** Essa alteração busca especificar uma versão consistente para o MySQL, o que pode ajudar a evitar problemas inesperados causados por atualizações automáticas para as novas versões "latest".

---

**Commit** 3ed5f25471bd7c4cdc883cdb0df1693e7d76ad16:

Neste commit, foram adicionadas duas novas validações no `ValidationUtils` para checar se uma lista não é nula ou vazia, e para garantir que uma lista retornada nunca seja nula. Além disso, a versão da imagem do MySQL usada no docker-compose foi atualizada para `8.2.0-oraclelinux8`, garantindo a utilização da versão correta no README.

**Arquivos Alterados:** `README.md`, `ValidationUtils.java`.

**Alterações:**

- Duas novas validações foram adicionadas ao `ValidationUtils`. Essas validações são para verificar se uma lista não é nula ou vazia e garantir que uma lista retornada nunca seja nula.
- A versão da imagem do MySQL usada no `docker-compose.yaml` foi atualizada para `8.2.0-oraclelinux8`.

**Nota:** A principal ênfase deste commit é melhorar a lógica de validação em `ValidationUtils` e garantir a utilização da versão correta da imagem do MySQL no docker-compose.

---

**Commit** 7b42fab658fc5a362de4fafe57a540868cada521:

A adição deste código permite que os abrigos recebam doações. Isso é realizado por meio da criação de uma lista de doações, que é então anexada ao respectivo abrigo. Validações adicionais garantem que a lista de doações não seja nula ou vazia e que o abrigo exista no repositório. Testes foram adicionados para cobrir esses novos métodos e validações.

**Arquivos Alterados:** `ShelterEntityService.java`, `ShelterEntityServiceImpl.java`

**Alterações:**

- O método `receiveDonation()` foi adicionado à interface `ShelterEntityService`. Este método aceita um `ReceiveDonationRequest`, processa as doações, associa-as ao abrigo correspondente e retorna uma `ReceiveDonationResponse`. Se o abrigo não for encontrado, o método retornará null.
  
- `ReceiveDonationResponse receiveDonation(ReceiveDonationRequest request)` foi adicionado na implementação `ShelterEntityServiceImpl` de `ShelterEntityService`. O método atualiza os registros presistentes, valida os dados de entrada e atualiza o estado da aplicação. Essa implementação lança uma `ShelterEntityFailuresException` se a requisição de doação for nula ou vazia.
  
- Além disso, foram adicionados dois novos métodos privados no `ShelterEntityServiceImpl`. Os métodos `appendDonationsToShelter()` e `mergeDonationLists()` são auxiliares para o `receiveDonation()`.

**Nota:** Este commit se concentra em permitir que os abrigos recebam doações por meio da adição de novos métodos e validações. A ênfase principal está na validação dos dados de entrada e atualização do estado persistente.

---

**Commit** 6d4fcf74d709dc84a80c734f745df80a330a4340:

Este commit adiciona um novo recurso no `ShelterController` chamado 'receiveDonation'. Este novo método recebe e processa solicitações POST para o recebimento de doações. Ele registra a doação no back-end e responde com os detalhes da doação recebida, incluindo informações sobre o abrigo e o responsável. As classes relevantes para solicitar e responder também foram importadas.

**Arquivos Alterados:** `ShelterController.java`, `ShelterControllerImpl.java`, `ShelterService.java` e outros.

**Alterações:**

- Adicionado novo método 'receiveDonation' em `ShelterController` e `ShelterControllerImpl` que recebe e processa solicitações POST para o recebimento de doações.
- Adicionado o código necessário para registrar a doação no back-end e responder com os detalhes da doação recebida.
- Importadas as classes relevantes para solicitar e responder.

**Nota:** Este commit é principalmente sobre a adição de um novo recurso para processar e receber doações em abrigos. Este recurso também inclui o registro de doações e retorna uma resposta contendo os detalhes da doação.

---

**Commit** b43d290431ff1c0d8a2f604baf78dbeba293af65:

Este commit inclui a implementação do JavaDoc no método `findUserByEmail` e a implementação do Swagger.

**Arquivos Alterados:** `UserController.java`, `UserEntityService.java`

**Alterações:**

- O método `findUserByEmail` no `UserController.java` recebeu um comentário extenso do JavaDoc explicando sua funcionalidade e também a implementação do Swagger, responsável por documentar e descrever as operações do método.
- Foi adicionado um comentário do JavaDoc para o método `findUserByEmail` na `UserEntityService.java` detalhando seu propósito e comportamento.

**Nota:** A ênfase principal deste commit é melhorar a documentação e a descrição das funções, facilitando a compreensão e o uso por outros desenvolvedores.

---

**Commit** 350c50820a257a7e828a36a1ca2aeb86c4898289:

Este commit traz pequenas atualizações nas descrições do JavaDoc e testes no método `findUserByEmail`.

**Arquivos Alterados:** `UserController.java`, `UserEntityServiceImplTest.java`

**Alterações:**

- Em `UserController.java`, uma linha do JavaDoc no método `findUserByEmail` que descrevia o parâmetro foi removida. O parâmetro em questão não é usado na implementação do método, tornando a descrição irrelevante.

- No arquivo `UserEntityServiceImplTest.java`, houve a inclusão de um teste feito para assegurar que o retorno do método `findUserByEmail` seja um objeto não nulo.

**Nota:** Este commit aprimora a consistência do código, eliminando comentários desnecessários e garantindo a presença de testes relevantes.

---

**Commit** c9f30632bc67732f04a84760835bb55fc68db1ff:

Este commit adiciona classes de exceção personalizadas e atualizações enum `ExceptionDetails`.

**Arquivos Alterados:** `ExceptionDetails.java`, `CustomException.java`, `DetailsFailureException.java`

**Alterações:**

- A enumeração `ExceptionDetails.java` foi expandida para incluir novos códigos de termos e mensagens correspondentes a erros e exceções específicas. Um novo método também foi adicionado para recuperar detalhes de exceção com base em um termo.
- Duas novas classes de exceção personalizadas, `CustomException.java` e `DetailsFailureException.java`, foram criadas. Elas fornecem manipulação de erros e exceções mais robusta e permitem o rastreamento de erros mais refinado.

**Nota:** A principal ênfase deste commit é melhorar o gerenciamento de erros e exceções na aplicação. Ao personalizar a manipulação de erros, o aplicativo pode fornecer feedback mais útil em casos de falha e pode rastrear melhor onde e por que essas falhas estão ocorrendo.

---

**Commit** 3d8aa4e9c41d775cd99bc05a7e943f5782e2b943:

Este commit atualiza as classes de exceção para estenderem `CustomException`. Também modifica suas estruturas para utilizar termos em seus construtores. Esses termos são usados para buscar os detalhes correspondentes da exceção lançada. Além disso, o processo de validação de exceções personalizadas foram reformulados em `ValidationUtils`.

**Arquivos Alterados:** `AddressEntityFailuresException.java`, `ConstructorDefaultUndefinedException.java`, `DonationEntityFailuresException.java`, `MapperFailureException.java`, entre outros.

**Alterações:**

- As classes de exceção como `AddressEntityFailuresException.java`, `ConstructorDefaultUndefinedException.java`, `DonationEntityFailuresException.java` e `MapperFailureException.java` foram atualizadas para estender `CustomException` em vez de `RuntimeException`.
- Os construtores nestas classes de exceção agora requerem um integer 'termo' que é usado para buscar informações detalhadas sobre a exceção.
- Além da mensagem de exceção e Throwable 'cause', os construtores nas classes de exceção também levam um termo que é usado para buscar os detalhes da exceção.
  

**Nota:** A principal ênfase deste commit é aprimorar a manipulação personalizada de exceções fornecendo termos específicos. Os termos permitem recuperar detalhes precisos de exceção para rastreabilidade aprimorada.

---

**Commit** e2f253b88508be00afcee028855bf1a43baa6f81:

Este commit inclui a constante CLASS_MAPPING_FAILURE na interface `MapperStrategy` e na classe `BuilderMapper`. Este valor é utilizado para melhorar as mensagens de erro quando um erro de mapeamento de classe ocorre, tornando-as mais informativas e úteis para rastrear o problema.

**Arquivos Alterados:** `BuilderMapper.java`, `MapperStrategy.java`

**Alterações:**

- A constante `CLASS_MAPPING_FAILURE` foi adicionada à interface `MapperStrategy` e à classe `BuilderMapper`.
- No construtor da classe `BuilderMapper`, a constante `CLASS_MAPPING_FAILURE` é utilizada nas mensagens de exceção para `ConstructorDefaultUndefinedException` e `MapperFailureException`.

**Nota:** A principal ênfase desta confirmação é melhorar a rastreabilidade dos erros de mapeamento de classe, fornecendo mensagens de erro mais informativas.

---

**Commit** b77edf4fd52a59bd3492cdf5e245c6e60326a597:

Este commit atualiza o tratamento de exceções no mapeamento de entidade de endereço. Substitui `MapperFailureException` por `ExceptionDetails` e faz alterações correspondentes no código para tratar as exceções de forma mais consistente. As mensagens de erro também foram atualizadas para utilizar códigos de erro em vez de strings literais. Todos os testes de unidade afetados foram atualizados de acordo.

**Arquivos Alterados:** `AddressEntityMapper.java`, `AddressMapper.java`, `AddressEntityServiceImpl.java`, `AddressEntityMapperTest.java`, `AddressMapperTest.java`

**Alterações:**

- Substituição da exceção `MapperFailureException` por `ExceptionDetails` para tratamento de exceções mais consistente.
- Atualização das mensagens de erro para utilizar códigos de erro em vez de strings literais.
- Atualização de todos os testes de unidade afetados pelas mudanças.
  

**Nota:** A principal ênfase desta confirmação é aprimorar o tratamento de exceções, tornando-o mais consistente e informativo.

---

**Commit** 4bfba94e14fa15eab72802b934be48d784512f61:

Este commit atualiza a manipulação de exceções nos mapeadores de doação e nos testes relacionados para fornecer mensagens de erro mais precisas. As dependências desnecessárias foram removidas e as mensagens de erro foram atualizadas para usar os detalhes específicos da exceção. Esses ajustes ajudam a rastrear e resolver erros mais eficientemente.

**Arquivos Alterados:** `DonationEntityMapper.java`, `DonationMapper.java`, `DonationEntityServiceImpl.java`, `DonationEntityMapperTest.java`, `DonationMapperTest.java`

**Alterações:**

- Substituição do tratamento de exceção `MapperFailureException` por chamadas diretas ao método `mapFrom`.
- As mensagens de erro foram atualizadas para usar os detalhes específicos da exceção.
- Inclusão de uma lógica que garante que a entidade a ser persistida não seja nula no arquivo `DonationEntityServiceImpl.java`.

**Nota:** A principal ênfase desta confirmação é aprimorar o tratamento de exceções para fornecer mensagens de erro mais precisas.

---

**Commit** 73d60cae3828d21657858bb8462b8c4877500959:

Este commit substitui as mensagens de erro dinâmicas por códigos de erro em várias classes e testes relacionados às exceções. Esta alteração facilita a rastreabilidade e a interpretação de erros, pois eles são agora identificados com códigos consistentes em vez de mensagens de texto.

**Arquivos Alterados:** `ShelterEntityMapper.java`, `ShelterMapper.java`, `ShelterRepository.java`, `ShelterEntityServiceImpl.java`, `ShelterEntityMapperTest.java`

**Alterações:**

- As mensagens de erro em várias classes e testes foram substituídas por códigos específicos.
- Removido o tratamento de exceção `MapperFailureException` em `ShelterEntityMapper.java` e `ShelterMapper.java`, substituindo por chamadas diretas ao método class-specific `mapFrom`.
- As constantes de mensagem de erro no `ShelterRepository.java` e `ShelterEntityServiceImpl.java` foram alteradas para códigos de erro correspondentes.
- Testes foram atualizados para verificar as condições de erro apropriadas.

**Nota:** A principal ênfase desta confirmação é aprimorar a rastreabilidade e interpretação de erros usando códigos de erro em vez de mensagens de texto.

---

**Commit** 5a6f3e5d52a558ba53aff0fd80413e99f30eeffc:

Este commit atualiza validações e tratamento de exceção com detalhes de exceção. As mensagens de erro em várias classes e testes foram substituídas por códigos de erro, incluindo nas classes `UserEntityMapper`, `UserMapper` e `UserRepository`.

**Arquivos Alterados:** `UserEntityMapper.java`, `UserMapper.java`, `UserRepository.java`, `UserEntityServiceImpl.java`, `UserEntityMapperTest.java`

**Alterações:**

- As mensagens de erro em várias classes e testes foram substituídas por códigos específicos.
- A lógica de validação para as classes `UserEntityMapper`, `UserMapper` e `UserRepository` foi atualizada para usar a nova classe `ExceptionDetails` no mapeamento e nos serviços de teste.
- Também foram ajustadas validações para as classes `UserEntityMapper`, `UserMapper` e `UserRepository` para melhorar a precisão na verificação de dados.

**Nota:** A principal ênfase desta confirmação é aprimorar a rastreabilidade e interpretação de erros usando detalhes de exceção em vez de mensagens de texto.

---

**Commit** e2a7418514e639aa29b060f5055ed07c70faf500:

Este commit adiciona comentários de documentação detalhados para a classe CustomException e seus construtores. Além disso, há uma refatoração do tratamento de exceções personalizadas no ControllerExceptionHandler, simplificando o processo para lidar apenas com a classe base CustomException em vez de várias subclasses específicas. Esta mudança torna o código mais genérico e fácil de manter.

**Arquivos Alterados:** 

- `ControllerExceptionHandler.java`
- `CustomException.java`

**Alterações:**

- Adição de documentação de comentários detalhada para a classe `CustomException` e seus construtores.
- Refatoração do tratamento de exceções personalizadas no `ControllerExceptionHandler` para lidar apenas com a classe base `CustomException`.
- O tratador de exceções específico para `ConstructorDefaultUndefinedException`, `ShelterEntityFailuresException`, `AddressEntityFailuresException`, `MapperFailureException`, `UserEntityFailuresException`, `DonationEntityFailuresException` foram removidos.
- Adicionada uma nova função tratadora de exceções específica para `CustomException`, que captura a exceção e processa a criação de um `ExceptionDTO`, bem como um `ResponseEntity` contendo os detalhes de erro da exceção.

**Nota:** A principal ênfase desta confirmação é melhorar a documentação detalhada para a classe `CustomException` e simplificar o tratamento de exceções personalizadas no ControllerExceptionHandler para lidar apenas com a classe base `CustomException`, tornando o código mais genérico e fácil de manter.

---

**Commit** 8130c3d18bda82296864cb18675db7f6161137da:

Este commit refatora os métodos de teste e implementação na classe `UserEntityServiceImpl`. Isso inclui a simplificação dos métodos de teste em `UserEntityServiceImplTest` para reduzir redundâncias. Além disso, mudanças significativas foram feitas no arquivo `UserEntityServiceImpl`, principalmente na maneira como o `UserEntityMapper` é utilizado. O objetivo desta refatoração é proporcionar um código mais enxuto e legível.

**Arquivos Alterados:** `UserEntityServiceImpl.java`, `UserEntityServiceImplTest.java`

**Alterações:**

- Os métodos de teste em `UserEntityServiceImplTest` foram simplificados.
- Um novo método, `getUserEntityMapper()`, foi adicionado recentemente em `UserEntityServiceImpl`.
- O método `findUserByEmail` em `UserEntityServiceImpl` foi refatorado para utilizar `BuilderMapper.mapTo()` e `getUserEntityMapper()`.
- O método `createUserEntityFromCreationRequest` em `UserEntityServiceImpl` também foi refatorado para usar `getUserEntityMapper()`.

**Nota:** A ênfase principal deste commit é a redução de redundâncias no código de teste e a refatoração de como o `UserEntityMapper` é empregado na `UserEntityServiceImpl`.

---

**Commit** eae5e588ca8b08e9a16ed1255c0a5bdf15401956:

Este commit inclui a criação do método para buscar um abrigo pelo email do responsável. Além disso, inclui a implementação do swagger e Java Doc para este novo método.

**Arquivos Alterados:** `ShelterController.java`, `ShelterControllerImpl.java`, `ReceiveDonationResponseFromShelterEntityMapper.java`, `ReceiveDonationResponse.java`, `ShelterEntityService.java`

Alterações:

- Em `ShelterController.java`, foi adicionado um novo endpoint GET para busca de abrigo pelo email do responsável.
- `ShelterControllerImpl.java` foi atualizado para incluir a implementação deste novo método. 
- A classe `ReceiveDonationResponseFromShelterEntityMapper.java` foi renomeada para `ShelterInformationResponseFromShelterEntityMapper.java` e as devidas alterações foram feitas no código para refletir essa mudança. 
- Da mesma forma, `ReceiveDonationResponse.java` foi renomeado para `ShelterInformationResponse.java`.
- `ShelterEntityService.java` foi atualizado para refletir essas mudanças de nomenclatura.

**Nota:** A ênfase principal deste commit é estender a funcionalidade do sistema com a habilidade de buscar abrigos pelo email do responsável, melhorando a interface de programação de aplicativos com documentação adicional e organizando melhor o código com mudanças de nome apropriadas.

---

**Commit** 7a7d393df05fac3ee7b3549160dc354f650327ad:

Este commit adiciona documentações Javadoc para a classe `AddressEntityService` e sua implementação. As novas páginas HTML geradas pelo Javadoc para `AddressEntityService` e `AddressEntityServiceImpl` também foram incluídas. Essas páginas contêm informações detalhadas sobre a interface e suas implementações, incluindo métodos e descrições de uso.

**Arquivos Alterados:** `docs/allclasses-index.html`

**Alterações:**

- Foram adicionadas documentações Javadoc para os métodos em `AddressEntityService` e `AddressEntityServiceImpl`.
- Foram geradas novas páginas HTML pelo Javadoc para `AddressEntityService` e `AddressEntityServiceImpl`.

**Nota:** A ênfase principal deste commit é melhorar a documentação do código, fornecendo comentários Javadoc detalhados para a classe de serviço `AddressEntityService` e sua implementação, tornando mais fácil para os desenvolvedores entenderem o propósito e a funcionalidade dessas classes.

---