# Como Configurar CI/CD no GitHub

Os arquivos de workflow de CI/CD foram criados localmente mas não podem ser enviados automaticamente devido a restrições de permissões do GitHub App.

## Opção 1: Adicionar via Interface Web (Recomendado)

1. Acesse seu repositório no GitHub
2. Vá em **Actions** → **New workflow**
3. Clique em **set up a workflow yourself**
4. Nomeie o arquivo como `ci.yml`
5. Cole o conteúdo abaixo e clique em **Commit changes**

## Opção 2: Adicionar via Git Local

Se você tem acesso direto ao repositório (não via GitHub App):

```bash
git add .github/workflows/
git commit -m "Add CI/CD workflows"
git push
```

## Conteúdo do Workflow

Use o arquivo que está em `.github/workflows/ci.yml` no seu repositório local.

Após adicionar, a CI irá:
- ✅ Buildar o app Android e iOS em cada push/PR
- ✅ Executar testes
- ✅ Verificar qualidade de código
- ✅ Gerar artefatos (APK e Framework iOS)

## Arquivos Criados

Os seguintes arquivos de CI já estão criados localmente:

- `.github/workflows/ci.yml` - Pipeline principal de CI
- `.github/workflows/README.md` - Documentação dos workflows
- `radioplayerbr/gradlew` - Gradle wrapper (necessário para CI)
- `radioplayerbr/.gitattributes` - Garante permissões corretas

## Próximos Passos

Após adicionar o workflow:

1. Faça um push para `main` ou crie um PR
2. Veja a CI rodar na aba **Actions**
3. O badge no README ficará verde quando tudo passar! 🎉
