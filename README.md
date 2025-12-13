# [Java Notes]

A comprehensive collection of **Java** programming notes, code snippets, and concepts ranging from core syntax to advanced topics. Ideal for revision, learning, and interview preparation.

<a href="https://jitenraj.github.io/java-notes/" target="_blank">
  <img src="https://img.shields.io/badge/🚀_Live_Demo-Java_Notes-3b82f6?style=for-the-badge&logo=github&logoColor=white" alt="Live Demo" height="40" />
</a>

---

## 🎯 Goal of this Repository

This repository serves as a quick reference guide for:

1. **Revision:** Quick recap of concepts before interviews.
2. **Snippets:** Copy-paste ready code for standard algorithms and patterns.
3. **Deep Dives:** Understanding the *why* behind Java's behavior (OOPs, Design Patterns).

---

## 📚 Explore Topics

Select a topic to dive in. These notes are structured to be read linearly or used as a reference.

### ☕ Core Java

Foundational concepts required for every Java developer.

* **[Object Oriented Programming](https://jitenraj.github.io/java-notes/docs/java-oops)** - Covering key OOP concepts like Class, Object, Abstraction, Encapsulation, Inheritance, Polymorphism and Enum.

### 🚀 Advanced Java

Leveling up your skills for enterprise applications.

* **[Collections Framework](https://jitenraj.github.io/java-notes/coming-soon/)** - List, Set, Map internals and performance.
* **[Multithreading & Concurrency](https://jitenraj.github.io/java-notes/coming-soon/)** - Threads, Locks, Executors, CompletableFuture.
* **[Java 8+ Features](https://jitenraj.github.io/java-notes/coming-soon/)** - Lambdas, Streams API, Optional.

### 🛠️ Frameworks & Tools

* **[Design Patterns](https://jitenraj.github.io/java-notes/docs/java-design-patterns)** - Covering design patterns like creational, structural, and behavioral patterns.

---

> Note
>
> These notes are constantly being updated. If you see an error, please open an Issue!

---

### 🤝 Contributing

Contributions are welcome!

Thanks for your interest in improving these notes! Since this is a personal learning repository, contributions that fix errors or clarify complex topics are highly appreciated.

### How to Contribute

1. **Fixing Typos & Bugs**: If you find a spelling mistake or a code snippet that doesn't run, feel free to open a Pull Request (PR) directly.
2. **Adding New Topics**: If you want to add notes for a new Java concept, please open an **Issue** first so we can discuss where it fits in the structure.

### Steps to Submit Changes

1. **Fork** the repository.
2. **Clone** it locally. [(follow the instructions to run it)](#️-how-to-run-locally)
3. Create a feature branch (`git checkout -b feature/NewTopic`).
4. Make changes, add new topics, or a correction.
5. [Preview the site locally before pushing changes.](#3-run-the-local-server)
6. Commit your changes.
7. Open a Pull Request.
8. Provide instructions to check the effect of the changes.
9. Await code review.

Please ensure your code snippets are wrapped in markdown code blocks (e.g., ` ```java ... ``` `).

---

## 🛠️ How to Run Locally

Follow these steps to preview the site locally before pushing changes.

### 1. Prerequisites

Ensure you have the following installed:

* **Ruby**: (Version 2.7+ recommended)
* **RubyGems**: Usually comes with Ruby.
* **GCC/Make**:
  * *Mac:* Install Xcode Command Line Tools.
  * *Linux:* Install `build-essential`.
  * *Windows:* Use the RubyInstaller with DevKit.

### 2. Setup the Project

#### **Step 1: Clone the repo**

```bash
git clone "https://github.com/<Your-User-Name>/java-notes.git"

cd java-notes
```

#### **Step 2: Install Bundler**

```bash
gem install bundler
```

#### **Step 3: Configure "Clean" Install**

Tell bundler to install dependencies in a local folder so we don't clutter the system.

```bash
bundle config set --local path 'vendor/bundle'
```

#### **Step 4: Ignore the Dependencies**

Ensure your `.gitignore` file includes `vendor/` so you don't commit the libraries.

```bash
echo "vendor/" >> .gitignore
```

#### **Step 5: Install Dependencies**

```bash
bundle install
```

### 3. Run the Local Server

Start the Jekyll server. We use `bundle exec` to ensure it uses the local dependencies we just installed

```bash
bundle exec jekyll serve --livereload
```

### 4. Preview

Open your browser to: `http://localhost:4000/`

---

### ⚠️ Troubleshooting

If the site loads but **styles/CSS are missing**, it is likely a `baseurl` issue. Run this command instead to force the local URL to work.

```bash
bundle exec jekyll serve --baseurl ""
```

---

Happy Learning! ☕

---
