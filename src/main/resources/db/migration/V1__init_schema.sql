-- =========================================================
-- IusCloud - Core Service
-- Init schema: Branches, Schedules, Assignments
-- =========================================================

-- =========================
-- 1. Branches (Sucursales)
-- =========================
CREATE TABLE branches (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    name VARCHAR(150) NOT NULL,
    type VARCHAR(50) NOT NULL, -- PHYSICAL | VIRTUAL | HOME_OFFICE
    city VARCHAR(150) NOT NULL,
    country VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone1 integer NOT NULL,
    phone2 integer NULL,
    description VARCHAR(250) NULL,
    legal_representative VARCHAR(80) NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    deleted_at TIMESTAMP NULL
);

CREATE INDEX idx_branches_tenant
    ON branches (tenant_id);

-- =========================
-- 2. Branch Schedules
-- =========================
CREATE TABLE branch_schedules (
    id UUID PRIMARY KEY,
    branch_id UUID NOT NULL,
    day_of_week SMALLINT NOT NULL, -- 1=Monday ... 7=Sunday
    start_time TIME,
    end_time TIME,
    is_closed BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    deleted_at TIMESTAMP NULL,

    CONSTRAINT fk_branch_schedules_branch
        FOREIGN KEY (branch_id) REFERENCES branches(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_branch_schedules_branch
    ON branch_schedules (branch_id);

-- =========================
-- 3. Schedule Templates
-- =========================
CREATE TABLE schedule_templates (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    deleted_at TIMESTAMP NULL
);

CREATE INDEX idx_schedule_templates_tenant
    ON schedule_templates (tenant_id);

-- =========================
-- 4. Schedule Template Blocks
-- =========================
CREATE TABLE schedule_template_blocks (
    id UUID PRIMARY KEY,
    template_id UUID NOT NULL,
    day_of_week SMALLINT NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    deleted_at TIMESTAMP NULL,

    CONSTRAINT fk_template_blocks_template
        FOREIGN KEY (template_id) REFERENCES schedule_templates(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_template_blocks_template
    ON schedule_template_blocks (template_id);

-- =========================
-- 5. User ↔ Branch Assignments
-- =========================
CREATE TABLE user_branch_assignments (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    user_external_id UUID NOT NULL,
    branch_id UUID NOT NULL,
    schedule_type VARCHAR(30) NOT NULL,
    -- BRANCH | TEMPLATE | CUSTOM
    schedule_template_id UUID NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    deleted_at TIMESTAMP NULL,

    CONSTRAINT fk_assignment_branch
        FOREIGN KEY (branch_id) REFERENCES branches(id),

    CONSTRAINT fk_assignment_template
        FOREIGN KEY (schedule_template_id) REFERENCES schedule_templates(id)
);

CREATE INDEX idx_user_branch_assignments_tenant
    ON user_branch_assignments (tenant_id);

CREATE INDEX idx_user_branch_assignments_user
    ON user_branch_assignments (user_external_id);

CREATE INDEX idx_user_branch_assignments_branch
    ON user_branch_assignments (branch_id);

-- =========================
-- 6. Custom Schedule Blocks
-- =========================
CREATE TABLE user_branch_schedule_blocks (
    id UUID PRIMARY KEY,
    assignment_id UUID NOT NULL,
    day_of_week SMALLINT NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    deleted_at TIMESTAMP NULL,

    CONSTRAINT fk_assignment_schedule_blocks
        FOREIGN KEY (assignment_id) REFERENCES user_branch_assignments(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_assignment_schedule_blocks_assignment
    ON user_branch_schedule_blocks (assignment_id);