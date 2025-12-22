---
nav_exclude: true
search_exclude: true
---

# Development Command Reference

## 1. Ruby & Gems

General environment checks and system-level package management.

```ruby
# Check the installed Ruby version
ruby -v

# Check the current RubyGems version
gem -v

# Install a specific gem to the system
gem install <gem_name>

# Update RubyGems to the latest version
gem update --system

# List all gems currently installed on the system
gem list

```

---

## 2. Bundler

Managing project-specific dependencies and the local environment.

```bash
# Install Bundler (the manager for your Gemfile)
gem install bundler

# Create a new Gemfile in the current directory
bundle init

# Configure Bundler to install gems into 'vendor/bundle' for isolation
bundle config set --local path 'vendor/bundle'

# Install all gems defined in the Gemfile
bundle install

# Execute a command (like jekyll or rails) using the project's gems
bundle exec <command>

# Start the Jekyll server. 
# We use `bundle exec` to ensure it uses the local dependencies we just installed
bundle exec jekyll serve --livereload   

```

---

## 3. Gemfile Reference

Example structure for your `Gemfile` to manage dependencies.

```ruby
# Define where gems are downloaded from
source "[https://rubygems.org](https://rubygems.org)"

# Specify the project's Ruby version
ruby "3.2.2"

# Add a specific gem with version constraints
gem "jekyll", "~> 4.3.2"

# Group gems for specific environments (e.g., local testing)
group :development do
  gem "webrick"
end

```

---

## 4. Git & GitHub

Version control and remote repository synchronization.

### **Local Repository Setup**

```bash
# Initialize a new Git repository
git init

# Stage all files for the first commit
git add .

git add filename.extention

# Create the initial commit
git commit -m "Initial commit"

# Ensure the main branch is named 'main'
git branch -M main

```

### **GitHub Integration**

```bash
# Connect the local repo to a GitHub repository
git remote add origin [https://github.com/JitenRaj/java-notes.git](https://github.com/JitenRaj/java-notes.git)

# Push the local 'main' branch to GitHub for the first time
git push -u origin main

# Pull the latest changes from the remote repository
git pull origin main

```

### **Branching & Status**

```bash
# Check which files are changed or staged
git status

# Create and switch to a new development branch
git checkout -b <branch_name>

# Merge a branch into your current branch
git merge <branch_name>

# View a condensed history of commits
git log --oneline

```

---

## 5. Quick Workflow Summary

1. **Sync:** `git pull origin main`
2. **Install:** `bundle install`
3. **Run:** `bundle exec jekyll serve`
4. **Save:** `git add .` && `git commit -m "description"`
5. **Upload:** `git push origin main`
