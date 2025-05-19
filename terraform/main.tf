provider "google" {
  project = var.project_id
  region  = var.region
}

resource "google_cloud_run_service" "franchise-cloud-run" {
  name     = "franchise-cloud-run"
  location = "us-central1"

  template {
    spec {
      containers {
        image = "northamerica-northeast1-docker.pkg.dev/eco-mark/cloud-run-source-deploy/franchise/franchise-api:5991c4de6c4fc31709a2e6d9a330ba8676339e85"

        env {
          name  = "SPRING_R2DBC_URL"
          value = var.spring_r2dbc_url
        }

        env {
          name  = "SPRING_R2DBC_USERNAME"
          value = var.spring_r2dbc_username
        }

        env {
          name  = "SPRING_R2DBC_PASSWORD"
          value = var.spring_r2dbc_password
        }

        env {
          name  = "SPRING_PROFILES_ACTIVE"
          value = "docker"
        }
      }
    }
  }

  traffic {
    percent         = 100
    latest_revision = true
  }
  autogenerate_revision_name = true
}

resource "google_cloud_run_service_iam_policy" "pub1-access" {
    service = google_cloud_run_service.franchise-cloud-run.name
    location = google_cloud_run_service.franchise-cloud-run.location
    policy_data = data.google_iam_policy.pub1-access.policy_data
}

resource "google_cloud_run_service_iam_member" "invoker" {
  location = google_cloud_run_service.franchise-cloud-run.location
  service  = google_cloud_run_service.franchise-cloud-run.name
  role     = "roles/run.invoker"
  member   = "allUsers"
}