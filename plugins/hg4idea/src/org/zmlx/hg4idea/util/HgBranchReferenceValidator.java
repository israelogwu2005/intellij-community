/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zmlx.hg4idea.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zmlx.hg4idea.HgBundle;
import org.zmlx.hg4idea.repo.HgRepository;

import java.util.Collection;

public class HgBranchReferenceValidator extends HgReferenceValidator {
  private final HgRepository myRepository;

  public HgBranchReferenceValidator(@NotNull HgRepository repository) {
    myRepository = repository;
  }

  @Override
  protected boolean hasConflictsWithAnotherNames(@Nullable String name) {
    Collection<String> branches = myRepository.getBranches().keySet();
    String currentBranch = myRepository.getCurrentBranch(); //  branches set doesn't contain uncommitted branch -> need an addition check
    myErrorText = currentBranch.equals(name) || branches.contains(name)
                  ? HgBundle.message("hg4idea.branch.duplicated.name.error", name) : null;
    return myErrorText != null;
  }
}
