
# Sudoku em Java

Este projeto implementa um jogo de Sudoku simples no console, desenvolvido em Java. Ele permite aos jogadores iniciar um novo tabuleiro, preencher números, remover jogadas e verificar o status atual do jogo.

## Melhorias nas Mensagens de Exibição

Melhorias nas Mensagens de Exibição
Várias mensagens exibidas ao usuário foram aprimoradas para maior clareza e feedback:

- Mensagens de erro e status: A frase "O jogo ainda não foi iniciado iniciado" foi corrigida para "O jogo ainda não foi iniciado.", removendo a duplicação.

- Confirmação de limpeza: A pergunta "Tem certeza que deseja limpar seu jogo e perder todo seu progresso?" foi atualizada para "Tem certeza que deseja limpar seu jogo e perder todo seu progresso? (sim/não)", indicando claramente as opções de resposta.

### Feedback de operações:

- Ao colocar um número: Adicionada uma mensagem de sucesso "Número %d inserido com sucesso na posição [%d,%d]." após uma jogada válida.

- Ao remover um número: Adicionada uma mensagem de sucesso "Número removido com sucesso da posição [%d,%d]." após uma remoção válida.

- Após limpar o jogo: Adicionado feedback "Jogo limpo com sucesso!" ou "Operação de limpeza cancelada.".

- Orientação de entrada de dados: As mensagens para solicitar a coluna e linha foram aprimoradas para "Informe a coluna em que o número será inserido (0-8):" e "Informe a linha em que o número será inserido (0-8):", respectivamente, indicando os limites válidos.

- Erros na jogada: A mensagem "A posição [%s,%s] tem um valor fixo" foi estendida para "A posição [%s,%s] tem um valor fixo ou o movimento é inválido.", oferecendo uma explicação mais abrangente para a falha na inserção. Da mesma forma, para a remoção, a mensagem foi ajustada para "A posição [%s,%s] tem um valor fixo e não pode ser limpa.".

- Conclusão do jogo: A mensagem de parabéns ao finalizar o jogo foi ligeiramente ajustada para "Parabéns! Você concluiu o jogo!".

- Mensagens de erro de digitação: A mensagem "Seu jogo conté, erros" foi corrigida para "Seu jogo contém erros. Verifique seu tabuleiro e ajuste-o.".

- Essas alterações visam proporcionar uma experiência mais intuitiva e informativa para o usuário durante a interação com o jogo.

## 🔗 Links

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](www.linkedin.com/in/pedro-paulo-da-silva-neto-8b8a20368)
[![github](https://img.shields.io/badge/github-1DA1F2?style=for-the-badge&logo=github&logoColor=white)](https://github.com/PEDROPAULONETO)
