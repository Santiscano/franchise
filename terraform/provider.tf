terraform {
    required_providers {
        google = {
            source  = "hashicorp/google"
            version = "~> 3.5"
        }
    }
}


provider "google" {
    project     = "franchise-376920"
    region      = "us-crentral1"
    zone        = "us-central1-a"
    credentials = "./franchise-d9cb7f45448c.json"
}