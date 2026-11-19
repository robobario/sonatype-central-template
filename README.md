# template-library

Template for a single-library Maven project, published to Maven Central via
Sonatype's Central Portal, built on public GitHub with GitHub Actions.

## What this gives you

- `pom.xml` configured for `central-publishing-maven-plugin`: sources, javadoc
  and GPG signing on release, auto-published once validated.
- `.github/workflows/ci.yaml` — build and test on every push to `main` and
  every pull request.
- `.github/workflows/publish-snapshot.yaml` — deploys the current `-SNAPSHOT`
  to Central's snapshot repository on every push to `main`.
- `.github/workflows/release.yaml` — manual, single-step release: builds,
  signs, publishes to Central, tags, creates a GitHub release, bumps to the
  next snapshot.
- `.github/dependabot.yml` — weekly updates for Maven dependencies and GitHub
  Actions.

## Using this template

1. Rename the project: in `pom.xml` replace `groupId`, `artifactId`, `name`,
   `description`, `url`, `scm`, and the `developers` entry. Move
   `src/main/java/io/github/robobario/template` and
   `src/test/java/io/github/robobario/template` to your real package, update
   package declarations, delete `Greeter`/`GreeterTest`.
2. Follow [.github/README.md](.github/README.md) to set up Central Portal
   publishing, repository secrets, and the release workflow.
