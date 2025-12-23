---
layout: default
title: Projects
nav_order: 70
permalink: /projects/
---

## My Projects

Here is a list of my public projects and tools.

---

{% for project in site.data.projects %}

### {{ project.title }}

{% if project.status == "active" %}
Active
{: .label .label-green }
{% elsif project.status == "inprogress" %}
In Progress
{: .label .label-blue }
{% elsif project.status == "complete" %}
Complete
{: .label .label-purple}
{% elsif project.status == "coming-soon" %}
Coming Soon
{: .label .label-yellow }
{% elsif project.status == "deprecated" %}
Deprecated
{: .label .label-red }
{% endif %}

{{ project.desc }}

[View on GitHub]({{ project.href }}){: .btn .btn-outline .mt-3 }

---
{% endfor %}
