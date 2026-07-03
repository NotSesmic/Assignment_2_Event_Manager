document.getElementById("reg-form").addEventListener("submit", async (e) => {
  e.preventDefault();

  const form = e.target;
  const status = document.getElementById("status");
  const submitBtn = form.querySelector("button[type=submit]");

  const payload = {
    name: form.name.value,
    email: form.email.value,
    college: form.college.value,
    event_name: form.eventName.value, // maps to Registration.eventName via @JsonProperty
    phone: form.phone.value,
  };

  submitBtn.disabled = true;
  submitBtn.textContent = "Submitting…";
  status.className = "";
  status.textContent = "";

  try {
    // relative URL — frontend and backend are the same origin now
    const res = await fetch("/api/registrations", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });

    if (!res.ok) {
      const data = await res.json().catch(() => ({}));
      throw new Error(data.error || "Registration failed");
    }

    status.className = "success";
    status.textContent = "You're registered. Confirmation details will follow by email.";
    form.reset();
  } catch (err) {
    status.className = "error";
    status.textContent = err.message;
  } finally {
    submitBtn.disabled = false;
    submitBtn.textContent = "Submit registration";
  }
});
