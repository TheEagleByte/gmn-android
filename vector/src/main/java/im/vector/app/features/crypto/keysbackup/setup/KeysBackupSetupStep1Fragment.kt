/*
 * Copyright 2019 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.crypto.keysbackup.setup

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import im.vector.app.R
import im.vector.app.core.platform.VectorBaseFragment
import im.vector.app.core.utils.LiveEvent
import kotlinx.android.synthetic.main.fragment_keys_backup_setup_step1.*
import javax.inject.Inject

class KeysBackupSetupStep1Fragment @Inject constructor() : VectorBaseFragment() {

    override fun getLayoutResId() = R.layout.fragment_keys_backup_setup_step1

    private lateinit var viewModel: KeysBackupSetupSharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activityViewModelProvider.get(KeysBackupSetupSharedViewModel::class.java)

        viewModel.showManualExport.observe(viewLifecycleOwner, Observer {
            val showOption = it ?: false
            // Can't use isVisible because the kotlin compiler will crash with  Back-end (JVM) Internal error: wrong code generated
            advancedOptionText.visibility = if (showOption) View.VISIBLE else View.GONE
            manualExportButton.visibility = if (showOption) View.VISIBLE else View.GONE
        })

        keys_backup_setup_step1_button.setOnClickListener { onButtonClick() }
        manualExportButton.setOnClickListener { onManualExportClick() }
    }

    private fun onButtonClick() {
        viewModel.navigateEvent.value = LiveEvent(KeysBackupSetupSharedViewModel.NAVIGATE_TO_STEP_2)
    }

    private fun onManualExportClick() {
        viewModel.navigateEvent.value = LiveEvent(KeysBackupSetupSharedViewModel.NAVIGATE_MANUAL_EXPORT)
    }
}
