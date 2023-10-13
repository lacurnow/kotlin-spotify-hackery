# Constants

COLOR_BLUE=\033[0;34m
COLOR_NONE=\033[0m

# Targets

help:
	@grep -E '^[0-9a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) \
		| sort \
		| awk 'BEGIN {FS = ":.*?## "}; {printf "$(COLOR_BLUE)%s|$(COLOR_NONE)%s\n", $$1, $$2}' \
		| column -t -s '|'

.PHONY: clean
clean: ## Remove all built artefacts
	@echo 'Removing artefacts'

.PHONY: test
test: ## Run tests
	@echo 'Running tests'
	@gradle test

dist: test ## Build the distribution
	@echo 'Building the distribution'
	@gradle shadowJar

run: dist ## Build the distribution and run it
	@echo 'Running application'
	@java -jar ./build/libs/starter.server-all.jar

coverage: ## Run a test coverage report
	@gradle koverHtmlReport
	@open './build/reports/kover/html/index.html'

