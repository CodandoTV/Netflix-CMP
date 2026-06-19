---
name: open-pr
description: Open a Pull Request comparing current feature branch against master, generating a pt-br description
---

# open-pr

Open a Pull Request on GitHub comparing the current branch against `master`, generating a description in **pt-br**.

## Behavior

1. **Detect current branch** — run `git rev-parse --abbrev-ref HEAD` to get the feature branch name.

2. **Fetch latest `master`** — run `git fetch origin master`.

3. **Analyze changes** — compare HEAD against `master`:

   ```bash
   git log master..HEAD --oneline --no-decorate
   git diff master...HEAD --stat
   ```

4. **Categorize commits** by conventional commit type:

   | Type | Label |
   |---|---|
   | `feat` | Nova funcionalidade |
   | `fix` | Correção |
   | `refactor` | Refatoração |
   | `docs` | Documentação |
   | `test` | Testes |
   | `chore` | Manutenção |
   | `build` | Build / CI |
   | `BREAKING` | Mudança crítica |

5. **Identify affected modules** — from changed file paths, determine which modules were modified:
   - `composeApp`
   - `feature-*`
   - `core-*`
   - `build-logic`
   - `androidApp`
   - `gradle` / config

6. **Generate PR body in pt-br** with the following structure:

   ```markdown
   ## O que foi feito?

   [Resumo das alterações baseado nos commits]

   ## Módulos afetados

   - [lista de módulos]

   ## Tipo de mudança

   - [ ] Nova funcionalidade
   - [ ] Correção de bug
   - [ ] Refatoração
   - [ ] Documentação
   - [ ] Testes
   - [ ] Build / CI

   ## Como testar?

   1. [passos para testar]
   2. [passos adicionais]

   ## Checklist

   - [ ] Build passa localmente
   - [ ] Lint / Detekt não aponta erros novos
   - [ ] Testes existentes continuam passando
   - [ ] Testes novos foram adicionados (se aplicável)
   ```

7. **Determine PR title** — use the first commit subject line or a concise summary derived from the branch name (e.g. `feat: adiciona tela de perfil`).

8. **Open the PR** using `gh`:

   ```bash
   gh pr create \
     --base master \
     --head <current-branch> \
     --title "<title>" \
     --body "<body>"
   ```

9. If `gh` is not authenticated or available, print the full PR content to stdout with instructions.

## Notes

- All PR text MUST be in **pt-br** (Portuguese — Brazil).
- Follow the project's existing commit style (conventional commits).
- If the branch has only one commit, use its message as the PR title.
- If the PR would be empty (no diff), warn the user.
