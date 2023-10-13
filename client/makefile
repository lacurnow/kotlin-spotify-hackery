# Constants

COLOR_RED=\033[0;31m
COLOR_GREEN=\033[0;32m
COLOR_YELLOW=\033[0;33m
COLOR_BLUE=\033[0;34m
COLOR_NONE=\033[0m
COLOR_CLEAR_LINE=\r\033[K

# Targets

help:
	@grep -E '^[0-9a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) \
		| sort \
		| awk 'BEGIN {FS = ":.*?## "}; {printf "$(COLOR_BLUE)%s$(COLOR_NONE)|%s\n", $$1, $$2}' \
		| column -t -s '|'

.PHONY: clean
clean: ## Remove all built artefacts
	@echo 'Removing built artefacts'
	@rm -rf build

.PHONY: clean-all
clean-all: clean ## Remove all artefacts
	@echo 'Removing dependencies'
	@rm -rf node_modules

node_modules:
	@npm install

deps: node_modules

run: deps ## Run the application
	@npm start

dist: clean deps ## Build the distribution
	@echo "Building the distribution"
	@npm run build
